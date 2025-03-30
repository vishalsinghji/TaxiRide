import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getRides = async (params = {}) => {
  try {
    const response = await api.get('/rides', { params });
    console.log('Fetched rides:', response.data);
    return response.data?.content;
  } catch (error) {
    console.error('Error fetching rides:', error);
    throw error;
  }
};

export const getRideSummary = async () => {
  try {
    const response = await api.get('/rides/summary');
    console.log('Ride summary data:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error fetching ride summary:', error);
    throw error;
  }
};
