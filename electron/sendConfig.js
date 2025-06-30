import axios from 'axios';
import path from 'path';
import fs from 'fs';

import { fileURLToPath } from 'url';
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const sendConfig = async () => {

  try { 

    const configPath = path.join(__dirname, 'config.json');

    const config = JSON.parse(fs.readFileSync(configPath, 'utf-8'));
    // console.log('@@@@@@@@@@',  {
    //   dbUrl:`jdbc:mysql://${config.host}:${config.port}/${config.dbname}?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true`,
    //   username: `${config.username}`,
    //   password: `${config.password}`
    // });





    // Send configuration to the backend
    const response = await axios.post('http://localhost:8080/configapi/configuration', {
      dbUrl: `jdbc:mysql://${config.host}:${config.port}/${config.dbname}?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true`,
      username: `${config.username}`,
      password: `${config.password}`
    }, {
      headers: {
        'Content-Type': 'application/json',
      },

    });
    console.log('@@@ response', response);

    console.log('Config sent successfully:', response.data);
  } catch (error) {
    console.log('@@@@@@@@@', error);
    console.error('Error sending config:', error);
  }
};

export default sendConfig;
