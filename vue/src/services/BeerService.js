import axios from 'axios';

export default {
   getAllBeers() {
    const token = localStorage.getItem('token');
    return axios.get('/beers', {headers: { Authorization: `Bearer ${token}` }})
   },
   getBeersByBrewery(breweryId) {
    const token = localStorage.getItem('token');
        return axios.get(`/breweries/${breweryId}/beers`, { headers: { Authorization: `Bearer ${token}` } });
   },
   getBeerById(beerId) {
    const token = localStorage.getItem('token');
    return axios.get(`/beers/${beerId}`, { headers: { Authorization: `Bearer ${token}` } })
   }
   // getBeerById for beer details (another page or hover?)
   // addBeerToBrewery
  }