/*
 * Login page placeholder for the Medical Centre application.
 * In production, this would validate credentials against Keycloak.
 */
import React from 'react';

export const LoginPage = () => {
  return (
    <div className="bg-white p-8 rounded-lg shadow-md max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-6">Login</h2>
      <p>Please log in to access the Medical Centre dashboard.</p>
      <p>Contact your administrator for credentials.</p>
      <p style={{ marginTop: '2rem' }}>
        <a href="/register" className="underline">Don't have an account? Register here</a>
      </p>
    </div>
  );
};

export default LoginPage;