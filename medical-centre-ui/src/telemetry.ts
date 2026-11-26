/**
 * OpenTelemetry Browser Setup for Medical Centre UI
 * Must be imported FIRST in main.tsx before any other imports
 */

import { WebTracerProvider } from '@opentelemetry/sdk-trace-web';
import { ZoneContextManager } from '@opentelemetry/context-zone';
import { registerInstrumentations } from '@opentelemetry/instrumentation';
import { FetchInstrumentation } from '@opentelemetry/instrumentation-fetch';
import { DocumentLoadInstrumentation } from '@opentelemetry/instrumentation-document-load';
import { OTLPTraceExporter } from '@opentelemetry/exporter-trace-otlp-http';
import { Resource } from '@opentelemetry/resources';
import { BatchSpanProcessor } from '@opentelemetry/sdk-trace-base';

// Initialize the context manager for async propagation
const zoneContextManager = new ZoneContextManager();
zoneContextManager.enable();

// Create resource with service metadata.
// Plain string keys are used for portability across @opentelemetry/semantic-conventions versions.
const resource = new Resource({
  'service.name': 'medical-centre-ui',
  'service.version': '1.0.0',
  'deployment.environment': import.meta.env.MODE || 'development',
});

// Configure OTLP HTTP exporter to send traces to OpenTelemetry Collector
const otlpEndpoint = import.meta.env.VITE_OTEL_EXPORTER_OTLP_ENDPOINT || 'http://localhost:4318/v1/traces';
const otlpExporter = new OTLPTraceExporter({
  url: otlpEndpoint,
});

// Create tracer provider with resource and batch processor
const provider = new WebTracerProvider({
  resource,
  spanProcessors: [
    new BatchSpanProcessor(otlpExporter, {
      maxQueueSize: 1000,
      scheduledDelayMillis: 5000,
      maxExportBatchSize: 512,
    }),
  ],
});

// Register the provider globally
provider.register({
  contextManager: zoneContextManager,
});

// Auto-instrument fetch/XHR and document load
registerInstrumentations({
  instrumentations: [
    new DocumentLoadInstrumentation(),
    new FetchInstrumentation({
      // Propagate trace context headers to all fetch requests
      propagateTraceHeaderCorsUrls: [
        /^http:\/\/localhost:8080/, // API Gateway
        /^http:\/\/localhost:8081/, // Patient Service
        /^http:\/\/localhost:8082/, // Medic Service
        /^http:\/\/localhost:8083/, // Appointment Service
        /^http:\/\/localhost:8084/, // Notification Service
        /^http:\/\/localhost:8085/, // Identity Service
      ],
      // Clear timing resources to avoid memory leaks
      clearTimingResources: true,
      // Apply custom attributes to all fetch spans
      applyCustomAttributesOnSpan: (span) => {
        span.setAttribute('app.name', 'medical-centre-ui');
      },
    }),
  ],
});

console.info('[OTel] Browser tracing initialized', {
  endpoint: otlpEndpoint,
  serviceName: 'medical-centre-ui',
});

// Export a named tracer for manual instrumentation
export const tracer = provider.getTracer('medical-centre-ui');

// Helper to create a child span for custom operations
export function createSpan(name: string, attributes?: Record<string, string | number | boolean>) {
  return tracer.startSpan(name, { attributes });
}