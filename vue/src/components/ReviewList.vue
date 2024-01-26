<!-- ReviewList.vue -->
<template>
  <div class="review-list">
    <div class="review" v-for="review in reviews" :key="review.review_id">
      <div class="card">
        <div class="user-info">
          <h2 class="user-name">{{ getUsername(review.user_id) }}</h2>
          <span class="date">{{ getDate(review.date_of_review) }}</span>
        </div>

        <div class="rating">
            <h3 class="review-title">{{ review.title }}</h3>
          <img
            src="../assets/star.png"
            :title="review.rating + ' Star Review'"
            class="rating-star"
            v-for="star in review.rating"
            :key="star"
          />
        </div>

        <p class="review-text">{{ review.review_text }}</p>
      </div>
    </div>
  </div>
</template>

  <script>
  export default {
    props: {
      reviews: Array,
      getUsername: Function,
    },
    methods: {
    getDate( reviewDate) {
      const currentDate = Date.now();
      const reviewDateTime = new Date(reviewDate);
      const timeDifference = currentDate - reviewDateTime;
      const minutes = Math.floor(timeDifference / (1000 * 60));
      const hours = Math.floor(minutes / 60);
      const days = Math.floor(hours / 24);
      const months = Math.floor(days / 30);
      const years = Math.floor(days / 365);

      if (years > 0) {
    return `Posted ${years} ${years === 1 ? 'year' : 'years'} ago`;
  } else if (months > 0) {
    return `Posted ${months} ${months === 1 ? 'month' : 'months'} ago`;
  } else if (days > 0) {
    return `Posted ${days} ${days === 1 ? 'day' : 'days'} ago`;
  } else if (hours > 0) {
    return `Posted ${hours} ${hours === 1 ? 'hour' : 'hours'} ago`;
  } else if (minutes > 0) {
    return `Posted ${minutes} ${minutes === 1 ? 'minute' : 'minutes'} ago`;
  } else {
    return 'Posted just now';
  }

    },
  },
  };
  </script>
  <!-- ReviewList.vue -->
  <style>
  .review-list {
    display: flex;
    justify-content: center; /* Center horizontally */
    align-items: center; /* Center vertically */
    height: 100vh; /* 100% of the viewport height */
  }

  .review {
    margin: 10px;
  }

  .card {
    border: 3px solid #0000ff;
    border-radius: 10px;
    padding: 15px;
    width: 300px; /* Adjust the width as needed */
    background-color: #5a92e1;
  }

  .user-info {
    display: flex;
    align-items: center; /* Align items vertically in the middle */
    justify-content: space-between;
  }

  .user-name {
    margin-right: 10px; /* Adjust the margin as needed */
  }
  .rating {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .review-title {
    flex: 1;
  }
  .rating-star {
  width: 15px; /* Adjust the size of the stars */
  height: 15px; /* Adjust the size of the stars */
  margin-left: 5px;
}

  /* Your existing styles */
  </style>
