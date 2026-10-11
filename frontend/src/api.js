import axios from 'axios';
const api=axios.create({baseURL:import.meta.env.VITE_API_URL||'https://shelfwise-backend-production.up.railway.app/api',timeout:60000});
api.interceptors.request.use(config=>{const token=localStorage.getItem('library_token');if(token)config.headers.Authorization=`Bearer ${token}`;return config;});
export default api;
