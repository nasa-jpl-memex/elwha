/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nasa.jpl.memex.elwha.impl;

import java.util.ArrayList;
import java.util.Arrays;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;

import gov.nasa.jpl.memex.elwha.Elwha;

@RestLiCollection(name = "elwha", namespace = "gov.nasa.jpl.memex.elwha")
public class ElwhaResource extends CollectionResourceTemplate<String, Elwha> {

  /**
   * Main entry of this application.
   *
   * @param userIds follow(comma separated user ids) track(comma separated filter terms)
   * @throws TwitterException when Twitter service or network is unavailable
   * @return
   */
  @Override
  public Elwha get(String userIds) {
    //return new Elwha().setMessage("Hello, Rest.li!");
    // }

    //if (userIds.length < 1) {
    //  System.out.println("Usage: java twitter4j.examples.PrintFilterStream [follow(comma separated numerical user ids)] [track(comma separated filter terms)]");
    //  System.exit(-1);
    //}

    StatusListener listener = new StatusListener() {
      @Override
      public void onStatus(Status status) {
        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
      }

      @Override
      public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
      }

      @Override
      public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
      }

      @Override
      public void onScrubGeo(long userId, long upToStatusId) {
        System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
      }

      @Override
      public void onStallWarning(StallWarning warning) {
        System.out.println("Got stall warning:" + warning);
      }

      @Override
      public void onException(Exception ex) {
        ex.printStackTrace();
      }
    };

    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
    twitterStream.addListener(listener);
    ArrayList<Long> follow = new ArrayList<Long>();
    ArrayList<String> track = new ArrayList<String>();
    if (isNumericalArgument(userIds)) {
      for (String id : userIds.split(",")) {
        follow.add(Long.parseLong(id));
      }
    } else {
      track.addAll(Arrays.asList(userIds.split(",")));
    }
    long[] followArray = new long[follow.size()];
    for (int i = 0; i < follow.size(); i++) {
      followArray[i] = follow.get(i);
    }
    String[] trackArray = track.toArray(new String[track.size()]);

    // filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
    twitterStream.filter(new FilterQuery(0, followArray, trackArray));
    
    return new Elwha().setUserIds("Mock stub!");
  }

  private static boolean isNumericalArgument(String argument) {
    String args[] = argument.split(",");
    boolean isNumericalArgument = true;
    for (String arg : args) {
      try {
        Integer.parseInt(arg);
      } catch (NumberFormatException nfe) {
        isNumericalArgument = false;
        break;
      }
    }
    return isNumericalArgument;
  }
}
