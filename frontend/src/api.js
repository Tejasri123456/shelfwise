import axios from 'axios';

let rawUrl = import.meta.env.VITE_API_URL || 'https://shelfwise-backend-production.up.railway.app/api';
if (rawUrl && !rawUrl.startsWith('http://') && !rawUrl.startsWith('https://')) {
  rawUrl = 'https://' + rawUrl;
}

const api = axios.create({ baseURL: rawUrl, timeout: 60000 });
api.interceptors.request.use(config => {
  const token = localStorage.getItem('library_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});
export default api;
