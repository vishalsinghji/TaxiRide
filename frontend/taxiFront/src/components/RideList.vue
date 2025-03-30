<template>
    <div class="ride-list">
        <div class="input-container">
    <div class="input-row">
      <input class="neo-input" v-model="filters.minPrice" type="number" placeholder="Min Price" />
      <input class="neo-input" v-model="filters.maxPrice" type="number" placeholder="Max Price" />
      <input class="neo-input" v-model="filters.startDate" type="date" />
    </div>
    <div class="input-row">
      <input class="neo-input" v-model="filters.endDate" type="date" />
      <input class="neo-input" v-model="filters.startLocation" placeholder="Start Location" />
      <input class="neo-input" v-model="filters.endLocation" placeholder="End Location" />
      <div class="button-container">
      <button class="glassmorphism-button" @click="fetchRides">Apply Filters</button>
    </div>
    </div>
    
  </div>
      <table class="glassmorphism-table w-full border-collapse">
        <thead>
          <tr>
            <th>Start Place</th>
            <th>End Place</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="ride in rides" :key="ride.id">
            <td>{{ ride?.startPlace }}</td>
            <td>{{ ride?.endPlace }}</td>
            <td>{{ ride?.startDate }}</td>
            <td>{{ ride?.endDate }}</td>
            <td>{{ ride?.price }}</td>
          </tr>
        </tbody>
      </table>
      <div class="controls">
        <select style="margin: 0.2rem;" v-model="sortBy" @change="fetchRides" class="neo-select">
          <option value="price">Price</option>
          <option value="startDate">Start Date</option>
          <option value="endDate">End Date</option>
        </select>
        <button style="margin: 0.2rem;" class="glassmorphism-button mt-4" @click="changePage(-1)" :disabled="page <= 1">Prev</button>
        <span style="color: blue;">Page {{ page }}</span>
        <button  style="margin: 0.2rem;" class="glassmorphism-button mt-4" @click="changePage(1)">Next</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import {getRides} from '../services/api'; 
  
  const rides = ref([]);
  const filters = ref({
    minPrice: '',
    maxPrice: '',
    startDate: '',
    endDate: '',
    startLocation: '',
    endLocation: '',
  });
  const sortBy = ref('price');
  const page = ref(1);
  const size = ref(5);
  
  const fetchRides = async () => {
    try {

    const params = Object.fromEntries(
      Object.entries({
        ...filters.value,
        sortBy: sortBy.value,
        page: page.value,
        size: size.value,
      }).filter(([_, value]) => value !== '' && value !== null && value !== undefined)
    );
      rides.value = await getRides(params);
    } catch (error) {
      console.error('Failed to load rides:', error);
    }
  };
  
  const changePage = (offset) => {
    page.value += offset;
    fetchRides();
  };
  
  onMounted(fetchRides);
  </script>
  
  <style scoped>
  .ride-list {
    margin-top: 20px;
  }
  .filters input {
    margin-right: 10px;
    padding: 5px;
  }
  .controls {
    margin-top: 10px;
  }
  .glassmorphism-container {
  background: rgba(255, 255, 255, 0.5); 
  border-radius: 12px;
  backdrop-filter: blur(10px);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(0, 0, 0, 0.1);
}


.glassmorphism-table {
  width: 100%;
  border-radius: 5px;
  overflow: hidden;
}

.glassmorphism-table th, .glassmorphism-table td {
  padding: 12px;
  text-align: left;
  color: #333; 
}

.glassmorphism-table th {
  background: rgba(0, 0, 0, 0.05);
}

.glassmorphism-table tr:nth-child(even) {
  background: rgba(0, 0, 0, 0.02);
}

.glassmorphism-button {
  background: rgba(255, 255, 255, 0.6);
  color: #333;
  border: 1px solid rgba(0, 0, 0, 0.1);
  padding: 10px 20px;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  cursor: pointer;
  transition: all 0.3s ease;
}

.glassmorphism-button:hover {
  background: rgba(255, 255, 255, 0.8);
}

.input-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.input-row {
  display: flex;
  gap: 10px;
  justify-content: flex-start;
  width: 100%;
}

.neo-input {
  width: 200px;
  padding: 10px;
  font-size: 14px;
  border-radius: 12px;
  outline: none;
  border: none;
  background: #f0f0f3;
  box-shadow: 6px 6px 12px #c8c8c8, -6px -6px 12px #ffffff;
  transition: all 0.3s ease-in-out;
}

.neo-input:focus {
  box-shadow: inset 4px 4px 10px #c8c8c8, inset -4px -4px 10px #ffffff;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px;
  border-radius: 7px;
  background-color: #d2dbe3;
}

.neo-select {
  width: 100px;
  padding: 10px;
  border-radius: 7px;
  background: #bed3dc;
  border: none;
  outline: none;
  box-shadow: 6px 6px 10px rgba(0, 0, 0, 0.1), -6px -6px 10px rgba(255, 255, 255, 0.7);
  font-size: 16px;
  cursor: pointer;
  transition: 0.3s;
}

.neo-select:hover,
.neo-select:focus {
  box-shadow: inset 4px 4px 8px rgba(0, 0, 0, 0.1), inset -4px -4px 8px rgba(255, 255, 255, 0.7);
}
  table {
    width: 100%;
    margin-top: 10px;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }
  </style>
  