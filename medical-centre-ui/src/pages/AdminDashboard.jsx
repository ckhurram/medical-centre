/*
 * Admin Dashboard page placeholder for the Medical Centre application.
 */
import React from 'react';

export const AdminDashboard = () => {
  return (
    <div className="bg-white p-8 rounded-lg shadow-md max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-6">Admin Dashboard</h2>
      <p>Welcome to the Medical Centre admin dashboard.</p>
      <p>Features coming soon:</p>
      <ul className="list-disc list-inside mt-4 space-y-2">
        <li>Medic management</li>
        <li>Appointment scheduling</li>
        <li>Patient management</li>
        <li>Notifications</li>
      </ul>
    </div>
  );
};

export default AdminDashboard;