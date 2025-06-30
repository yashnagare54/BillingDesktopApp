document.getElementById('config-form').addEventListener('submit', (event) => {
    event.preventDefault();
  
    const config = {
      host: document.getElementById('host').value,
      port: document.getElementById('port').value,
      username: document.getElementById('username').value,
      password: document.getElementById('password').value,
      dbname: document.getElementById('dbname').value,
    };
  
    window.electron.saveConfig(config);
  });
  