/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ivory.oozie.workflow;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

import org.apache.ivory.Util;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class WorkflowUnmarshallingTest {

	@Test
	public void testValidWorkflowUnamrashalling() throws JAXBException,
			SAXException {
		Unmarshaller unmarshaller = Util
				.getUnmarshaller(org.apache.ivory.oozie.workflow.WORKFLOWAPP.class);
		Schema schema = Util.getSchema(WorkflowUnmarshallingTest.class
				.getResource("/workflow.xsd"));
		unmarshaller.setSchema(schema);
		JAXBElement<WORKFLOWAPP> workflowApp = (JAXBElement<WORKFLOWAPP>) unmarshaller
				.unmarshal(WorkflowUnmarshallingTest.class
						.getResourceAsStream("/workflow.xml"));
		WORKFLOWAPP app = workflowApp.getValue();
		Assert.assertEquals(app.getName(), "java-main-wf");
		Assert.assertEquals(
				((ACTION) app.getDecisionOrForkOrJoin().get(0)).getName(),
				"java-node");
	}

}
