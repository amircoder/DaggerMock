/*
 *   Copyright 2016 Fabio Collini.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package it.cosenonjaviste.daggermock.realworldapp.main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import it.cosenonjaviste.daggermock.realworldapp.App;
import it.cosenonjaviste.daggeroverride.R;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject MainPresenter presenter;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App app = (App) getApplication();
        app.getComponent().mainActivityComponentBuilder().mainActivityModule(new MainActivityModule(this)).build().inject(this);

        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.text);

        findViewById(R.id.reload).setOnClickListener(v -> presenter.loadData());
    }

    @Override
    public void showText(String text) {
        textView.setText(text);
    }
}
