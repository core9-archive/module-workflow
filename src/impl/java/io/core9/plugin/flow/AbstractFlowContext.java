package io.core9.plugin.flow;

import io.core9.plugin.cache.Core9Cache;
import io.core9.plugin.session.Session;

import java.util.HashMap;
import java.util.Map;

import au.com.ds.ef.StatefulContext;


public abstract class AbstractFlowContext extends StatefulContext {
	private static final long serialVersionUID = 7985403257628581699L;
	String result = "";
	Object data = "";
	private Map<String, Object> request = new HashMap<>();
	protected AbstractFlowStateHandler stateHandler;
	private String sessionId;
	private Map<String, Session> sessions;
	private Core9Cache cache;

	public String getResult() {
		return result;
	};

    public AbstractFlowContext setResult(String result) {
		this.result = result;
		return this;
	}

	public Object getData() {
		return data;
	}

	public AbstractFlowContext setData(Object data) {
		this.data = data;
		return this;
	}
	

	public AbstractFlowContext setStateHandler(AbstractFlowStateHandler stateHandlers) {
	    this.stateHandler = stateHandlers;
		return this;
    }

	public AbstractFlowStateHandler getStateHandler() {
	    return stateHandler;
    }

	public Map<String, Object> getRequest() {
	    return request;
    }

	public AbstractFlowContext setRequest(Map<String, Object> request) {
	    this.request = request;
		return this;
    }

	public String getSessionId() {
	    return sessionId;
    }

	public AbstractFlowContext setSessionId(String sessionId) {
	    this.sessionId = sessionId;
	    return this;
    }
	
	public AbstractFlowContext setCache(Core9Cache cache) {
		this.cache = cache;
	    return this;
    }

	public Core9Cache getCache() {
	    return cache;
    }

	public Session getSession(String sessionId) {
	    return sessions.get(sessionId);
    }

	public AbstractFlowContext setSession(Session session) {
	    sessions.put(session.getSessionId(), session);
	    return this;
    }
}
