import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/myapi': {
        target: 'http://localhost:8080', // The backend server URL
        changeOrigin: true,           // Change the origin of the host header to the target URL
        rewrite: (path) => path.replace(/^\/myapi/, ''), // Rewrite the path to match the target
      },
    },
  },
  base: './',
})
