<!-- ReviewForm.vue -->
<template>
    <div>
      
    <form v-if="!submitted" @submit.prevent="submitReview">
      <h2>Add Review</h2>
        <label for="rating">Rating:</label>
        <select v-model="rating" id="rating" required>
          <option value="1">1 Star</option>
          <option value="2">2 Stars</option>
          <option value="3">3 Stars</option>
          <option value="4">4 Stars</option>
          <option value="5">5 Stars</option>
        </select>
  
        <label for="title">Title:</label>
        <input v-model="title" id="title" type="text" required />
  
        <label for="reviewText">Review:</label>
        <textarea v-model="reviewText" id="reviewText" required></textarea>
  
        <button type="submit">Submit Review</button>
      </form>
      <div v-else aria-hidden="true">
      <p>Thank you for your feedback!</p>
    </div>

    </div>
  </template>
  
  <script>
import reviewService from '../services/ReviewService';

export default {
  props: {
    beerId: Number,
    reviews: Array,
  },
  data() {
    return {
      rating: 1,
      title: '',
      reviewText: '',

    };
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    },

  },
  mounted() {
    // Call checkUserReview when the component is mounted
    this.checkUserReview();
  },
  methods: {
    async submitReview() {
      try {
        // Check if the user has reviewed
          const newReview = {
            beer_id: this.beerId,
            user_id: this.currentUser.id,
            review_text: this.reviewText,
            rating: this.rating,
            title: this.title,
            date_of_review: Date.now(),
          };

          // Add the new review
          await reviewService.addReview(newReview);

          // Emit an event to inform the parent component to update reviews
          this.$emit('review-added');

          // Reset form
          this.rating = 1;
          this.title = '';
          this.reviewText = '';

          // Inform user that the review has been submitted
          alert('Review submitted successfully!');
        
      } catch (error) {
        console.error('Error submitting review:', error);
      }
    },

  },
};
</script>
  
  /* Add this to your ReviewForm.vue or in a separate styles file */
<style scoped>
  form {
    background-repeat: no-repeat;
  background-size: cover;
  /* Adjust this property as needed */
  background-position: top left;
  /* Top-left justified */
  background-color: #B2D3F9;
  /* Fallback background color, Possible to change this to white? */
  border: 4px solid #2328DA;
  border-radius: 10px;
  padding: 1rem;
  width: 20vw;
  max-width: 90%;
  height: auto;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 1);
  /* Adds shadow to Sign In */
  margin-top: 50px;
  /* Spacing from the top */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  z-index: 2;
  }

  label {
    display: block;
    margin-top: 10px;
    text-align: left;
  }

  select,
  input,
  textarea {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    margin-bottom: 15px;
    box-sizing: border-box;
  }

  button {
    background-color: #4caf50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  button:hover {
    background-color: #45a049;
  }

  .feedback-message {
    margin-top: 10px;
    color: green;
    font-weight: bold;
  }

  div[aria-hidden="true"] {
  display: none;
}
</style>

  