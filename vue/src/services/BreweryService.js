import axios from 'axios';

export default {
    getBreweries() {
      const token = localStorage.getItem('token');
      return axios.get('/breweries', { headers: { Authorization: `Bearer ${token}` } });
    },
    getBreweryById(breweryId) {
        const token = localStorage.getItem('token');
        return axios.get(`/breweries/${breweryId}`, { headers: { Authorization: `Bearer ${token}` } });
      },
      addBrewery(brewery){
        const token = localStorage.getItem('token');
        return axios.post('/breweries', brewery, {headers: { Authorization: `Bearer ${token}` }});
      }
  }