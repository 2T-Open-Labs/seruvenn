package com.ikite.seruvenn.Commons.stream;

import org.apache.kafka.streams.kstream.KStream;

import com.ikite.seruvenn.Commons.message.Message;

@SuppressWarnings("rawtypes")
public interface IStreamBase {

	public KStream<String, Message> buildForwardStream();
	public KStream<String, Message> buildReverseStream();
	
}
