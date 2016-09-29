package com.cooksys.backend.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.backend.beans.dao.FlightDao;
import com.cooksys.core.models.Flight;

@Component
@Scope("session")
public class Subscriber {

	private List<Flight> flights;
	private List<Flight> flightMessages = new ArrayList<Flight>();
	private final String TOPIC_NAME = "FlightUpdate";

	private ActiveMQConnectionFactory connectionFactory;
	private ActiveMQConnection connection;

	private TopicSession topicSession;

	private Topic topicDestination;

	@Autowired
	private FlightDao flightDao;

	@PostConstruct
	public void init() throws JMSException {
		
		flights = new ArrayList<>(flightDao.getFlightModel().getFlights());
		this.connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		this.connection = (ActiveMQConnection) this.connectionFactory.createConnection();

		this.topicSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();

		this.topicSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		this.topicDestination = this.topicSession.createTopic(TOPIC_NAME);

		TopicSubscriber recv = this.topicSession.createSubscriber(this.topicDestination);

		recv.setMessageListener((objectMessage) -> {
			try {

				if (objectMessage instanceof ObjectMessage) {
					ObjectMessage msg = (ObjectMessage) objectMessage;
					String flightStat = msg.getStringProperty("FlightStatus");
					if (flightStat.equalsIgnoreCase("Flight Arrived")) {
						flightMessages = new ArrayList<>();
						Flight flight = (Flight) msg.getObject();
						flightMessages.add(flight);
					} else {
						flightMessages = (List<Flight>) msg.getObject();
						
					}

					for (Flight flight : flights) {
						for (Flight flightMessage : flightMessages)
							if (flightMessage.getFlightId().equals(flight.getFlightId())) {
								flight.setDeparture(flightMessage.getDeparture());
								flight.setFlightStatus(flightStat);
								System.out.println("flight status:" + flightStat);
							}
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		});

	}
}
