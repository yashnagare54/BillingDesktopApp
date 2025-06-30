import { app, BrowserWindow, ipcMain } from 'electron';
import path from 'path';
import { spawn,exec } from 'child_process'; 
import os from 'os';
import fs from 'fs';
import { fileURLToPath } from 'url';
import express from 'express';
import { createProxyMiddleware } from 'http-proxy-middleware';
import net from 'net';  
import sendConfig from './sendConfig.js'; 

const __filename = fileURLToPath(import.meta.url); 
const __dirname = path.dirname(__filename); 

let mainWindow;
let configWindow;
let springBootProcess;
let expressServer;
const expressApp = express();
const port = 3000;
const springBootPort = 8080; 

const appPath = app.getAppPath();
const jarPath = path.join(appPath, 'app-0.0.1-SNAPSHOT.jar');
console.log(jarPath);


// Serve static files from the 'dist' directory
expressApp.use(express.static(path.join(appPath, 'dist')));

// Proxy API requests to the backend server
expressApp.use('/myapi', createProxyMiddleware({
  target: `http://localhost:${springBootPort}`, 
  changeOrigin: true,
  pathRewrite: { '^/myapi': '' }, 
}));

expressApp.get('*', (req, res) => {
  res.sendFile(path.join(appPath, 'dist', 'index.html'));
});

function startExpressServer() {
  expressServer = expressApp.listen(port, (err) => {
    if (err) {
      console.error(`Error starting Express server: ${err.message}`);
      return;
    }
    console.log(`Server running at http://localhost:${port}`);
  });
}

function stopExpressServer() {
  if (expressServer) {
    expressServer.close(() => {
      console.log(`Express server stopped on port ${port}`);
    });
    expressServer = null;
  }
}

  function startSpringBoot() {
  // Check if the JAR file exists
  if (!fs.existsSync(jarPath)) {
    console.error(`JAR file not found at path: ${jarPath}`);
    return; 
  }

  isPortInUse(springBootPort, async(inUse) => {
    if (inUse) {
      console.log(`Port ${springBootPort} is currently in use. Trying to free it...`);
        exec(`for /f "tokens=5" %a in ('netstat -aon ^| findstr :${springBootPort}') do taskkill /PID %a /F`, (error) => {
        if (error) {
          console.error(`Error killing process on port ${springBootPort}: ${error.message}`);
        }
        startSpringBootProcess();
      });
    } else {
      startSpringBootProcess();
    }
  });
}

function startSpringBootProcess() {
  
 
  
  springBootProcess = spawn('java', ['-jar', jarPath, '']); 
  
  springBootProcess.stdout.on('data', (data) => {
    console.log(`Spring Boot stdout: ${data}`);
  });

  springBootProcess.stderr.on('data', (data) => {
    console.error(`Spring Boot stderr: ${data}`);
  });

  springBootProcess.on('error', (error) => {
    console.error(`Error starting Spring Boot app: ${error.message}`);
  });

  springBootProcess.on('exit', (code) => {
    console.log(`Spring Boot process exited with code ${code}`);
    springBootProcess = null; 
  });

  process.on('exit', () => stopSpringBoot());
  process.on('SIGINT', () => {
    stopSpringBoot();
    process.exit(0);
  });
  process.on('SIGTERM', () => {
    stopSpringBoot();
    process.exit(0);
  });
}

function stopSpringBoot() {
  if (springBootProcess) {
    springBootProcess.kill('SIGTERM');
    springBootProcess = null;
  }
}

function isPortInUse(port, callback) {
  const server = net.createServer();
  server.once('error', (err) => {
    if (err.code === 'EADDRINUSE') {
      callback(true);
    } else {
      callback(false);
    }
  });
  server.once('listening', () => {
    server.close(() => {
      callback(false);
    });
  });
  server.listen(port);
}

function createWindow() {
  mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      contextIsolation: true,
      worldSafeExecuteJavaScript: true,
      nodeIntegration: false,
    },
    autoHideMenuBar: true,
    maximizable: true,
    resizable: true,
  });

  mainWindow.loadURL(`http://localhost:${port}`);
  
  const configPath = path.join(appPath, 'config.json');
  if (!fs.existsSync(configPath)) {
    createConfigWindow();
  }

  ipcMain.on('save-config', async (event, config) => {
    fs.writeFileSync(configPath, JSON.stringify(config, null, 2));
    await sendConfig();
    console.log('sendConfigCalled');
    event.sender.send('config-saved');
  });

  mainWindow.on('closed', () => {
    mainWindow = null;
  });
}

function createConfigWindow() {
  configWindow = new BrowserWindow({
    width: 400,
    height: 300,
    modal: true,
    parent: mainWindow,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      contextIsolation: true,
      worldSafeExecuteJavaScript: true,
      nodeIntegration: false,
    },
  });
  configWindow.loadFile(path.join(__dirname, 'config.html'));
}

app.on('ready', () => {
  startSpringBoot();
  startExpressServer();
  
  createWindow(); 
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    stopExpressServer();
    stopSpringBoot();
    app.quit();
  }
});

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow();
  }
});
