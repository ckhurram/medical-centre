import React from 'react';
import { PatientRegistrationForm } from '../components/PatientRegistrationForm';

export const RegisterPatient = () => {
  return (
    <div className="bg-white p-8 rounded-lg shadow-md max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-6">Patient Registration</h2>

      <PatientRegistrationForm />

      <p className="text-center mt-4">
        Already have an account? <a href="/login" className="underline">Login here</a>
      </p>
    </div>
  );
};

export default RegisterPatient;