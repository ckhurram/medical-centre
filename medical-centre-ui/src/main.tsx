/**
 * Medical Centre UI - Entry Point
 * IMPORTANT: telemetry.ts must be imported FIRST to initialize OpenTelemetry
 * before any other application code runs.
 */
import './telemetry';

// React + App imports
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);