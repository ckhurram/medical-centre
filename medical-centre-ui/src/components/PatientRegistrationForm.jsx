/*
 * Patient Registration Form component
 * Validates fields and calls API to register a new patient
 */
import React, { useState } from 'react';

export const PatientRegistrationForm = () => {
  const [email, setEmail] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [phone, setPhone] = useState('');
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState({});
  const [success, setSuccess] = useState(false);
  const [patientId, setPatientId] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrors({});
    setSuccess(false);
    setLoading(true);

    const payload = {
      email,
      firstName,
      lastName,
      dateOfBirth,
      phone
    };

    try {
      const response = await fetch('http://localhost:8080/api/v1/patients', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload)
      });

      if (response.status === 201) {
        const result = await response.json();
        setPatientId(result.patientId || '');
        setSuccess(true);
      } else if (response.status === 400) {
        const errorData = await response.json();
        setErrors(errorData.errors || {});
        // Set general error for display
        const generalError = errorData.detail || 'Registration failed';
        setErrors(prev => ({ ...prev, general: generalError }));
      }
    } catch (err) {
      // Network error
      setErrors(prev => ({ ...prev, general: 'Network error. Please try again.' }));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto py-8" style={{ maxWidth: '450px' }}>
      <h2 className="text-2xl font-bold mb-6">Register as a Patient</h2>

      {success && (
        <div className="mb-4 p-4 rounded bg-green-100 text-green-800">
          <p>Registration successful! Your account is now active.</p>
          {patientId && <p>Patient ID: {patientId}</p>}
        </div>
      )}

      <form onSubmit={handleSubmit} noValidate style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
        {/* Email */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Email address
            <span className="text-red-500">*</span>
          </label>
          <input
            type="email"
            id="email"
            name="email"
            autoComplete="email"
            required
            className={`w-full px-3 py-2 rounded border ${errors.email ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-red-500`}
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          {errors.email && <p className="text-sm text-red-600">{errors.email}</p>}
        </div>

        {/* First Name */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            First name
            <span className="text-red-500">*</span>
          </label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            autoComplete="given-name"
            required
            className={`w-full px-3 py-2 rounded border ${errors.firstName ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-red-500`}
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
          {errors.firstName && <p className="text-sm text-red-600">{errors.firstName}</p>}
        </div>

        {/* Last Name */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Last name
            <span className="text-red-500">*</span>
          </label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            autoComplete="family-name"
            required
            className={`w-full px-3 py-2 rounded border ${errors.lastName ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-red-500`}
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
          {errors.lastName && <p className="text-sm text-red-600">{errors.lastName}</p>}
        </div>

        {/* Date of Birth */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Date of birth
            <span className="text-red-500">*</span>
          </label>
          <input
            type="date"
            id="dateOfBirth"
            name="dateOfBirth"
            required
            className={`w-full px-3 py-2 rounded border ${errors.dateOfBirth ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-red-500`}
            value={dateOfBirth}
            onChange={(e) => setDateOfBirth(e.target.value)}
          />
          {errors.dateOfBirth && <p className="text-sm text-red-600">{errors.dateOfBirth}</p>}
        </div>

        {/* Phone */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Phone number
            <span className="text-red-500">*</span>
          </label>
          <input
            type="tel"
            id="phone"
            name="phone"
            autoComplete="tel"
            required
            className={`w-full px-3 py-2 rounded border ${errors.phone ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-red-500`}
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
          />
          {errors.phone && <p className="text-sm text-red-600">{errors.phone}</p>}
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full px-4 py-2 rounded border border-gray-300 text-sm font-medium text-gray-700 bg-white hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-500"
          style={{ cursor: loading ? 'not-allowed' : 'pointer' }}
        >
          {loading ? 'Creating account...' : 'Register'}
        </button>
      </form>

      <p className="text-sm text-center text-gray-500 mt-4">
        Already have an account? <a href="/login" className="text-blue-600 underline">Login here</a>
      </p>
    </div>
  );
};

export default PatientRegistrationForm;