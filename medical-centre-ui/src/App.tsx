/**
 * Minimal App component - placeholder for UI implementation
 * Replace with actual application routing and components
 */
import React from 'react';

function App() {
  return (
    <div style={{ padding: '2rem', fontFamily: 'system-ui, sans-serif' }}>
      <h1>Medical Centre Appointment Booking</h1>
      <p>OpenTelemetry tracing is active. Check traces in Jaeger at <a href="http://localhost:16686" target="_blank" rel="noopener noreferrer">http://localhost:16686</a></p>
      <p>Metrics in Grafana at <a href="http://localhost:3001" target="_blank" rel="noopener noreferrer">http://localhost:3001</a></p>
      <p>Logs in Loki via Grafana Explore</p>
    </div>
  );
}

export default App;