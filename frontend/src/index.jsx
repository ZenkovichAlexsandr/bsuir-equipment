
import React from 'react';
import ReactDOM from 'react-dom';
import { AppContainer } from 'react-hot-loader'; // required

import App from './components/App.jsx';

import './index.scss';

function renderApp() {
    // We now render `<AppContainer>` instead of our App component.
    ReactDOM.render(
        <AppContainer>
            <App />
        </AppContainer>,
        document.getElementById('main')
    );
}

renderApp(); // Renders App on init

if (module.hot) {
    // Renders App every time a change in code happens.
    module.hot.accept('./components/App.jsx', renderApp);
}