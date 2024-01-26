<template>
  <div v-if="brewery" class="brewery-details">
    <!-- Display the uploaded image -->
    <div v-if="brewery.imagePath" class="image-container">
      <img :src="brewery.imagePath" alt="Brewery Image" class="brewery-image" />
    </div>

    <div class="brewery-info">
      <h1>{{ brewery.name }}</h1>
      <p> <strong>Founded:</strong> {{ formatDate(brewery.foundedDate) }}</p>
      <p><strong>Contact Phone:</strong>  {{ brewery.contactPhone }}</p>
      <p> <strong>Address:</strong>
         {{ brewery.streetAddress }}, {{ brewery.city }},
        {{ brewery.state }} {{ brewery.zipCode }}
      </p>
      <p> <strong>Days of Operation:</strong> {{ brewery.daysOfOperation }}</p>
      <p> <strong>Hours of Operation:</strong> {{ brewery.hoursOfOperation }}</p>
    </div>
  </div>

  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    brewery: Object,
    currentUser: Object,
  },
  data() {
    return {
      selectedFile: null,
    };
  },
  methods: {
    onFileChange(e) {
      this.selectedFile = e.target.files[0];
    },
    formatDate(dateString) {
      // Convert the date string to a Date object
      const date = new Date(dateString);

      // Format the date using toLocaleDateString method
      return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
      });
    },
    uploadImage() {
      const formData = new FormData();
      formData.append("image", this.selectedFile);

      axios
        .post("http://localhost:8080/upload-image", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          if (response.data && response.data.imagePath) {
            this.brewery.imagePath = response.data.imagePath;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style>
.brewery-details {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  background-color: #5a92e1;
  border: 4px solid #2328DA;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 1);
}

.image-container {
  margin-bottom: 20px;
}

.brewery-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
}
.brewery-info {
  text-align: left;
}

.brewery-info h1 {
  font-size: 24px;
  margin-bottom: 10px;
  text-align: center;
}
</style>
