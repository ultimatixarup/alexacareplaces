/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.careplaces.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CallDoctorConfirmationIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CallDoctorConfirmation"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	


        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        String doctorSlot = getSlotValue(intentRequest.getIntent().getSlots());
        String speechText = "Calling "+doctorSlot;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CarePlaces", speechText)
                .withShouldEndSession(true)
                .build();
    }

    private String getSlotValue(Map<String, Slot> slots) {
        for (Slot slot : slots.values()) {
            if (slot.getValue() != null) {
                return slot.getValue();
            }
        }
        return "Doctor Dilip Parekh";
    }

}
