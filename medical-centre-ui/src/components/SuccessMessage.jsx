/**
 * Minimal success message component displayed after successful patient registration.
 */
import React from 'react';

export const SuccessMessage = () => {
  // Access patientId from URL query params or session storage
  const urlParams = new URLSearchParams(window.location.search);
  const patientId = urlParams.get('patientId');

  return (
    <div className="bg-green-100 p-4 rounded-lg text-green-800 mb-6">
      <h3 className="text-lg font-bold">Registration Successful!</h3>
      <p>Welcome to the Medical Centre, patient #{patientId || '###'}</p>
      <p>You can now <a href="/login" className="underline">login</a> to book appointments.</p>
      <button
        onClick={() => window.location.href = '/'}
        className="mt-4 inline-block px-4 py-2 rounded bg-gray-200 text-sm font-medium text-gray-800 hover:bg-gray-300">
        Return to Homepage
      </button>
    </div>
  );
};

export default SuccessMessage;