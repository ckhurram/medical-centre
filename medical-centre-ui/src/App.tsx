/*
 * Minimal App component - registers routes for the Medical Centre application.
 * IMPORTANT: telemetry.ts must be imported FIRST in main.tsx before any other imports.
 */
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';

import LoginPage from './pages/LoginPage';
import RegisterPatient from './pages/RegisterPatient';
import AdminDashboard from './pages/AdminDashboard';
import AppointmentsPage from './pages/AppointmentsPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} exact={true} />
        <Route path="/register" element={<RegisterPatient />} />
        <Route path="/dashboard" element={<AdminDashboard />} />
        <Route path="/appointments" element={<AppointmentsPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;