# Taxi Ride Application

This repository contains the Taxi Ride Application, which consists of the following services:
1. **Ingestion Server** - Handles data ingestion.
2. **Taxi Ride Server (Backend)** - Provides APIs for ride management.
3. **Frontend** - A Vue.js application for visualization.


## Project Structure
```
TaxiRide/
│── backend/ingestion/   # Data ingestion service
│── backend/taxiride/   # Backend (Spring Boot)
│── frontend/           # Vue.js frontend
│── README.md           # Documentation
```


## Running Services 

### 🏗 Backend (Spring Boot)
#### 1. Navigate to Backend Folder
```sh
cd backend/taxiride
```
#### 2. Run Backend with Docker
```sh
docker build -t taxiride .
docker run -p 8080:8080 taxiride
```
Or run without Docker:
```sh
mvn spring-boot:run
```

#### 1. Navigate to Backend Folder for Ingestion 
```sh
cd backend/ingestion
```
#### 2. Run Backend with Docker
```sh
docker build -t ingestion .
docker run -p 8080:8080 ingestion
```
Or run without Docker:
```sh
mvn spring-boot:run
```


### 🎨 Frontend (Vue.js)
#### 1. Navigate to Frontend Folder
```sh
cd frontend/taxiFront
```
#### 2. Install Dependencies
```sh
npm install
```
#### 3. Run Frontend
```sh
npm run dev
```
Access the UI at `http://localhost:5173/`

## Stopping the Application
```sh
docker-compose down
```

## Environment Variables
Modify the `.env` files inside `taxi-ride-server` and `frontend` directories for custom configurations.


