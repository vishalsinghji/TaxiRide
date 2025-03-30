<template>
  <div class="dashboard">
    <h2 class="dashboard-title">🚖 Rides Dashboard</h2>

    <div class="stats">
      <div class="stat-card">📌 Total Rides: <span>{{ totalRides }}</span></div>
      <div class="stat-card">💰 Average Price: <span>{{ averagePrice }}</span></div>
      <div class="stat-card">📍 Most Common Pickup: <span>{{ mostFrequentStartLocation }}</span></div>
      <div class="stat-card">📍 Most Common Dropoff: <span>{{ mostFrequentEndLocation }}</span></div>
    </div>

    <h2 class="rides-title">📋 All Rides</h2>
    <RideList />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import {getRideSummary} from '../services/api'; 
import RideList from './RideList.vue'; 

const totalRides = ref(0);
const averagePrice = ref(0);
const mostFrequentStartLocation = ref('');
const mostFrequentEndLocation = ref('');

const loadDashboardData = async () => {
  try {
    const summary = await getRideSummary();
    totalRides.value = summary.totalRides;
    averagePrice.value = summary.averagePrice;
    mostFrequentStartLocation.value = summary.mostFrequentStartLocation;
    mostFrequentEndLocation.value = summary.mostFrequentEndLocation;
  } catch (error) {
    console.error('Failed to load dashboard data:', error);
  }
};

onMounted(loadDashboardData);
</script>

<style scoped>
.dashboard {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  padding: 20px;
  font-family: 'Poppins', sans-serif;
  color: #333;
}

.dashboard-title {
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 20px;
  background: linear-gradient(to right, #64175e, #00c6ff);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 15px;
  width: 100%;
  max-width: 800px;
  margin-bottom: 30px;
}

.stat-card {
  padding: 15px;
  border-radius: 15px;
  background: rgba(60, 101, 235, 0.2);
  backdrop-filter: blur(10px);
  box-shadow: 4px 4px 12px rgba(212, 160, 231, 0.1);
  font-size: 18px;
  font-weight: 500;
  color: #5f8cdb;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
  transition: 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 6px 6px 15px rgba(0, 0, 0, 0.2);
}

.stat-card span {
  font-weight: bold;
  font-size: 20px;
  color: #ffeb3b;
}

.rides-title {
  font-size: 22px;
  font-weight: bold;
  color: #444;
  margin-bottom: 15px;
  text-transform: uppercase;
}
</style>
