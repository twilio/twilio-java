package com.twilio.sdk.resource.instance.taskrouter;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;
import com.twilio.sdk.resource.list.taskrouter.WorkerReservationList;

/**
 * Workers represent an entity that is able to perform tasks, such as an agent working in a call center,
 * or a salesperson handling leads.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/workers">the TaskRouter documentation</a>.
 */
public class Worker extends NextGenInstanceResource<TwilioTaskRouterClient> {

  private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

  private static JSONParser parser = new JSONParser();

  /**
   * Instantiates a worker.
   *
   * @param client the client
   */
  public Worker(final TwilioTaskRouterClient client) {
    super(client);
  }

  /**
   * Instantiates a worker.
   *
   * @param client the client
   * @param properties the properties
   */
  public Worker(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
    super(client, properties);
  }

  /**
   * Instantiates a worker.
   *
   * @param client the client
   * @param workspaceSid the workspace sid
   * @param workerSid the worker sid
   */
  public Worker(final TwilioTaskRouterClient client, final String workspaceSid, final String workerSid) {
    super(client);
    if (StringUtils.isBlank(workspaceSid)) {
      throw new IllegalArgumentException("The workspaceSid for an Worker cannot be null");
    }
    if (StringUtils.isBlank(workerSid)) {
      throw new IllegalArgumentException("The workerSid for an Worker cannot be null");
    }
    setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
    setProperty(SID_PROPERTY, workerSid);
  }

  /**
   * Update a worker's attributes and/or friendly name and/or activity
   * @param attributes attributes of a worker
   * @param friendlyName friendly name of a worker
   * @param activitySid activity of a worker
   * @throws TwilioRestException 
   */
  public void update(final Map<String, String> attributes, final String friendlyName, final String activitySid) throws TwilioRestException {
    Map<String, String> params = new HashMap<String, String>();
    if(attributes != null) {
      params.put("Attributes", JSONObject.toJSONString(attributes));
    }else {
      params.put("Attributes", "{}");
    }
    if(friendlyName != null) {
      params.put("FriendlyName", friendlyName);
    }
    if(activitySid != null) {
      params.put("ActivitySid", activitySid);
    }
    this.update(params);
  }

  /**
   * Update a worker's activity
   * @param activitySid the activitysid to update the worker to
   * @throws TwilioRestException
   */
  public void updateActivity(final String activitySid) throws TwilioRestException {
    if (StringUtils.isBlank(activitySid)) {
      throw new IllegalArgumentException("The activitySid for updating a Worker's Activity cannot be null");
    }
    Map<String, String> params = new HashMap<String, String>();
    params.put("ActivitySid", activitySid);
    this.update(params);
  }

  /**
   * The ID of the {@link com.twilio.sdk.resource.instance.Account} that owns this worker.
   *
   * @return the account sid
   */
  public String getAccountSid() {
    return getProperty("account_sid");
  }

  /**
   * String describing the worker's current activity, for example 'on-call', 'after-call-work', 'break', etc.
   * Workers may only perform Activities that exist in this Workspace.
   *
   * @return the activity name
   */
  public String getActivityName() {
    return getProperty("activity_name");
  }

  /**
   * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Activity} this Worker is currently performing.
   *
   * @return the activity sid
   */
  public String getActivitySid() {
    return getProperty("activity_sid");
  }

  /**
   * A user-defined JSON object describing this Worker.
   *
   * @return the attributes
   */
  public String getAttributes() {
    return getProperty("attributes");
  }

  /**
   * A map that represents the JSON describing this Worker.
   *
   * @return the attributes
   * @throws ParseException 
   */
  public Map<String, Object> parseAttributes() throws ParseException {
    String attributes = getProperty("attributes");
    return (Map<String, Object>) parser.parse(attributes);
  }

  /**
   * The date and time this Worker was created.
   *
   * @return the date created
   */
  public Date getDateCreated() {
    return parseIsoDate(getProperty(DATE_CREATED_PROPERTY));
  }

  /**
   * The date and time this Worker's Activity status last changed.
   *
   * @return the date updated
   */
  public Date getDateStatusChanged() {
    return parseIsoDate(getProperty("date_status_changed"));
  }

  /**
   * The date and time this Worker was last updated.
   *
   * @return the date updated
   */
  public Date getDateUpdated() {
    return parseIsoDate(getProperty(DATE_UPDATED_PROPERTY));
  }

  /**
   * A human-readable name for this Worker.
   *
   * @return the friendly name
   */
  public String getFriendlyName() {
    return getProperty("friendly_name");
  }

  /**
   * This Worker's unique ID.
   *
   * @return the sid
   */
  public String getSid() {
    return getProperty(SID_PROPERTY);
  }

  /**
   * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workspace} containing this Worker.
   *
   * @return the workspace sid
   */
  public String getWorkspaceSid() {
    return getProperty(WORKSPACE_SID_PROPERTY);
  }

  /**
   * Whether this Worker can be assigned another {@link com.twilio.sdk.resource.instance.taskrouter.Task}.
   *
   * @return true, if available
   */
  public boolean isAvailable() {
    return (Boolean) getObject("available");
  }

  /**
   * Get worker statistics.
   * @return worker statistics
   */
  public WorkerStatistics getStatistics() {
    return getWorkerStatistics(new HashMap<String, String>());
  }

  /**
   * Get worker statistics.
   *
   * @param queryBuilder query builder which contains all parameters for the stats query request
   * @return worker statistics
   */
  public WorkerStatistics getWorkerStatistics(final StatisticsQueryBuilder queryBuilder) {
    Map<String, String> filters = new HashMap<String, String>();
    Calendar startDate = queryBuilder.getStartDate();
    Calendar endDate = queryBuilder.getEndDate();
    Integer minutes = queryBuilder.getMinutes();
    if(startDate != null) {
      filters.put("StartDate", formatCalendar(startDate));
    }
    if(endDate != null) {
      filters.put("EndDate", formatCalendar(endDate));
    }
    if(minutes != null) {
      filters.put("Minutes", minutes.toString());
    }
    return getWorkerStatistics(filters);
  }

  /**
   * Get worker statistics.
   *
   * @param filters the filters
   * @return worker statistics
   */
  public WorkerStatistics getWorkerStatistics(final Map<String, String> filters) {
    final String startDate = filters.get("StartDate");
    final String endDate = filters.get("EndDate");
    final String minutes = filters.get("Minutes");
    if((startDate != null || endDate != null) && minutes != null) {
      throw new IllegalArgumentException("Cannot provide Minutes in combination with StartDate or EndDate");
    }
    WorkerStatistics statistics = new WorkerStatistics(this.getClient(), this.getWorkspaceSid(), this.getSid(), filters);
    return statistics;
  }
  /**
   * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.WorkerReservationList} for this {@link
   * com.twilio.sdk.resource.instance.taskrouter.Workspace}.
   *
   * @return the {@link com.twilio.sdk.resource.list.taskrouter.WorkerReservationList}
   */
  public WorkerReservationList getReservations() {
    return getReservations(null);
  }

  /**
   * Retrieves the {@link com.twilio.sdk.resource.list.taskrouter.WorkerReservationList} for this {@link
   * com.twilio.sdk.resource.instance.taskrouter.Workspace}.
   *
   * @param filters for reservations
   * @return the {@link com.twilio.sdk.resource.list.taskrouter.WorkerReservationList}
   */
  public WorkerReservationList getReservations(final Map<String, String> filters) {
    return new WorkerReservationList(getClient(), getWorkspaceSid(), getSid(), filters);
  }

  @Override
    protected String getResourceLocation() {
      return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Workers/" +
        getSid();
    }
}
