// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['I was born in Ottawa, Canada.', 'My thumbs are different sizes.',
       'I have two cowlicks.', 'My favourite food is sushi.'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

async function showHello() {
    const responseFromServer = await fetch('/hello');
    const textFromResponse = await responseFromServer.json();

    //Pick a random message
    const msg = textFromResponse[Math.floor(Math.random() * textFromResponse.length)];
  
    const helloContainer = document.getElementById('hello-container');
    helloContainer.innerText = msg;
}

async function showContacts() {
    const responseFromServer = await fetch('/form-handler');
    const textFromResponse = await responseFromServer.json();

    const contactsContainer = document.getElementById('contacts-container');
    contactsContainer.innerText = textFromResponse;
}
