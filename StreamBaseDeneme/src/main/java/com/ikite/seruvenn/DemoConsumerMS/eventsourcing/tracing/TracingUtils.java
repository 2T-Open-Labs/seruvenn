package com.ikite.seruvenn.DemoConsumerMS.eventsourcing.tracing;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.TransformerSupplier;
import org.apache.kafka.streams.processor.ProcessorContext;

//import io.opentracing.Span;
//import io.opentracing.Tracer;
//import io.opentracing.propagation.Format;
//import io.opentracing.propagation.TextMap;
//import io.opentracing.tag.Tags;
//import io.opentracing.util.GlobalTracer;

@SuppressWarnings("unused")
public class TracingUtils {

//	public static TransformerSupplier<String, BodyEvent, KeyValue<String, ValueWithHeaders>> getTransformerSupplier() {
//
//		return new TransformerSupplier<String, BodyEvent, KeyValue<String, ValueWithHeaders>>() {
//			public Transformer<String, BodyEvent, KeyValue<String, ValueWithHeaders>> get() {
//				return new Transformer<String, BodyEvent, KeyValue<String, ValueWithHeaders>>() {
//
//					private ProcessorContext context;
//
//					@Override
//					public void init(ProcessorContext context) {
//						this.context = context;
//					}
//
//					@Override
//					public KeyValue<String, ValueWithHeaders> transform(String key, BodyEvent value) {
//						if (value == null) {
//							return KeyValue.pair(key, null);
//						}
//
//						injectFirstKafkaConsumerSpan(value, context);
//
//						return KeyValue.pair(key, new ValueWithHeaders(value, context.headers()));
//					}
//
//					@Override
//					public void close() {
//					}
//				};
//			}
//		};
//
//	}
//
//	/**
//	 * {@link io.opentracing.contrib.kafka.TracingKafkaConsumer} only builds and
//	 * injects a span when there is a parent context. This is the first consumer and
//	 * the beginning of our trace (i.e. there is no parent) so we are building and
//	 * injecting the first span manually. For more context, see the method
//	 * "buildAndFinishChildSpan" in
//	 * {@link io.opentracing.contrib.kafka.TracingKafkaUtils}.
//	 */
//	private static void injectFirstKafkaConsumerSpan(BodyEvent value, ProcessorContext context) {
//		Tracer tracer = GlobalTracer.get();
//		Tracer.SpanBuilder spanBuilder = tracer.buildSpan("receive").withTag(Tags.SPAN_KIND.getKey(),
//				Tags.SPAN_KIND_CONSUMER);
//
//		Span span = spanBuilder.start();
//		Tags.COMPONENT.set(span, "java-kafka");
//		Tags.PEER_SERVICE.set(span, "kafka");
//		span.setTag("partition", context.partition());
//		span.setTag("topic", context.topic());
//		span.setTag("offset", context.offset());
//		/* add the number (aka the first consumer record value) as a span tag */
//		// span.setTag(TRACING_NUMBER_VALUE, number);
//		span.finish();
//
//		TextMap headersMapInjectAdapter = new TextMap() {
//			@Override
//			public Iterator<Map.Entry<String, String>> iterator() {
//				throw new UnsupportedOperationException("iterator should never be used with Tracer.inject()");
//			}
//
//			@Override
//			public void put(String key, String value) {
//				context.headers().add(key, value.getBytes(StandardCharsets.UTF_8));
//			}
//		};
//		tracer.inject(span.context(), Format.Builtin.TEXT_MAP, headersMapInjectAdapter);
//	}
}
