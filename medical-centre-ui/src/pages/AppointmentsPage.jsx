/*
 * Appointments page placeholder for the Medical Centre application.
 */
import React from 'react';

export const AppointmentsPage = () => {
  return (
    <div className="bg-white p-8 rounded-lg shadow-md max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-6">Appointments</h2>
      <p>View and manage your appointments here.</p>
      <ul className="list-disc list-inside mt-4 space-y-2">
        <li>Book new appointment</li>
        <li>View upcoming appointments</li>
        <li>Cancel or reschedule</li>
      </ul>
    </div>
  );
};

export default AppointmentsPage;