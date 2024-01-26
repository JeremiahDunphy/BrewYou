import axios from "axios";

export default {
    getAllRatingsAndReviewsByBeer(beerId) {
        const token = localStorage.getItem('token');
        return axios.get(`/reviews/beer/${beerId}`, { headers: { Authorization: `Bearer ${token}` } })
    },
    addReview(review) {
        const token = localStorage.getItem('token');
        return axios.post('reviews', review, {headers: { Authorization: `Bearer ${token}` }})
    }
}