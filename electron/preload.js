// preload.mjs
// preload.js
const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electron', {
  saveConfig: (config) => ipcRenderer.send('save-config', config),
  onConfigSaved: (callback) => ipcRenderer.on('config-saved', (event, ...args) => callback(...args)),
});




window.addEventListener('DOMContentLoaded', () => {
  console.log('Preload script loaded successfully');
});
