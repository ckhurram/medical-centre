/*
 * API client module for patient registration and other API calls.
 * Uses axios interceptors for OpenTelemetry trace context propagation.
 */
import axios from 'axios';

// Base URL from environment - defaults to API Gateway
const API_BASE = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080';

// Export functions for patient registration and other API calls
export const patientRegistrationApi = async (payload) => {
  try {
    const response = await axios.post(
      `${API_BASE}/api/v1/patients`,
      payload,
      {
        headers: {
          'Content-Type': 'application/json',
          // OpenTelemetry: propagate trace context via traceparent header
          'traceparent': window.__traceparent || ''
        }
      }
    );
    return response;
  } catch (error) {
    // Map common errors
    if (error.response && error.response.status === 400) {
      throw new Error(error.response.data.detail || 'Validation error');
    }
    if (error.response && error.response.status === 409) {
      throw new Error('Email already registered');
    }
    throw new Error('Network error or server unavailable');
  }
};

// Helper function to create a traceparent header for distributed tracing
export const createTraceparent = (traceId, spanId = '00000000-00000000-00000000-0000') => {
  return `00-${traceId}-${spanId}-01`;
};

// Health check endpoint
export const healthCheckApi = async () => {
  try {
    const response = await axios.get(`${API_BASE}/actuator/health`);
    return response.data;
  } catch (error) {
    throw new Error('Service unavailable');
  }
};

// Get all patients (admin only - may require authentication)
export const listPatientsApi = async () => {
  try {
    const response = await axios.get(`${API_BASE}/api/v1/patients`, {
      headers: {
        'traceparent': window.__traceparent || ''
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error listing patients:', error);
    return [];
  }
};

// Get patient by ID
export const getPatientByIdApi = async (id) => {
  try {
    const response = await axios.get(`${API_BASE}/api/v1/patients/${id}`, {
      headers: {
        'traceparent': window.__traceparent || ''
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error getting patient by ID:', error);
    return null;
  }
};

export default {
  patientRegistrationApi,
  healthCheckApi,
  listPatientsApi,
  getPatientByIdApi
};