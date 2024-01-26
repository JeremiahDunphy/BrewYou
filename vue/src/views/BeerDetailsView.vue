<template>
  <div class="beer-details-page">
   <beerDetails :beer="beer"/>
   <button 
    v-if="!hasUserReviewed && !showReviewForm"
      class="toggle-button"
      @click="showAddReviewForm">
      Add Review
    </button>
   <addReviewForm v-if="showReviewForm" :beerId="beer ? beer.beerId : null"  :reviews="reviews" @review-added="updateReviews" />
   <reviewList :reviews="reviews" :getUsername="getUsername" />
  </div>
</template>
<script>
import beerService from '../services/BeerService';
import beerDetails from '../components/BeerDetails.vue';
import reviewService from '../services/ReviewService';
import userService from '../services/UserService';
import reviewList from '../components/ReviewList.vue';
import addReviewForm from '../components/AddReviewForm.vue';
export default {
    components :{
        beerDetails,
        reviewList,
        addReviewForm
    },
    data() {
        return {
            beer: null,
            reviews: [],
            users:[],
            showReviewForm: false,
      hasUserReviewed: false,
      showAddReviewButton: true,
        }
    },
    beforeRouteEnter(to, from, next) {
      const beerId = to.params.id;
      beerService
        .getBeerById(beerId)
        .then((response) => {
          next((vm) => vm.setData(response.data));
        })
        .catch((error) => {
          console.error('Error fetching beer data:', error);
          next(false);
        });
    },
    beforeRouteUpdate(to, from, next) {
    const beerId = to.params.id;
    this.fetchBeersByBrewery(beerId);
    next();
    },
    methods: {
    async setData(beer) {
      this.beer = beer;
      try {
        const response = await reviewService.getAllRatingsAndReviewsByBeer(beer.beerId);
        this.reviews = response.data;
        console.log(this.reviews);

        // Fetch usernames concurrently
        const userIds = this.reviews.map((review) => review.user_id);
        await this.fetchUsernames(userIds);
      } catch (error) {
        console.error('Error fetching reviews by beer:', error);
      }
    },
    async fetchUsernames(userIds) {
      try {
        const userResponses = await Promise.all(
          userIds.map((userId) => userService.getUserById(userId))
        );
        userResponses.forEach((response, index) => {
          const username = response.data.username || 'User Not Found';
          this.users[userIds[index]] = username;
        });
      } catch (error) {
        console.error('Error fetching usernames:', error);
      }
    },
    getUsername(userId) {
      return this.users[userId] || 'Anonymous';
    },
    updateReviews() {
      
      this.fetchReviews();
    },
    async fetchReviews() {
  try {
    const response = await reviewService.getAllRatingsAndReviewsByBeer(this.beer.beerId);
    this.reviews = response.data;
    // Fetch usernames concurrently
    const userIds = this.reviews.map((review) => review.user_id);
    await this.fetchUsernames(userIds);
  } catch (error) {
    console.error('Error fetching reviews:', error);
  }
},
showAddReviewForm() {
  this.showReviewForm = !this.showReviewForm;
      this.showAddReviewButton = false; // Hide the button
    },
  },
  watch: {
    // Watch for changes in reviews to update hasUserReviewed
    reviews: {
      handler(newReviews) {
        const currentUserReview = newReviews.find(
          (review) => review.user_id === this.$store.state.user.id
        );
        this.hasUserReviewed = !!currentUserReview;
      },
      immediate: true, // Call the handler immediately to set the initial value
    },
  },
};

</script>
<style>
 .beer-details-page {
    display: relative;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    background: url("@/assets/images/Brew_You_Running_Image.svg") no-repeat left top;
    background-size: cover;
    margin-top: 10px;
  }
  .toggle-button {
  border: 2px solid #0000ff;
  border-radius: 10px;
  padding: 0.5rem 1rem;
  background-color: #5a92e1;
  font-weight: 700;
  color:black;;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
  margin-bottom: 15px;
}

.toggle-button:hover {
  background-color: #21217c;
  color: white;
}

</style>
