/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.codingjam.github

import android.app.Application
import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import it.codingjam.github.di.AppComponent
import it.codingjam.github.di.AppModule
import it.codingjam.github.di.DaggerAppComponent
import timber.log.Timber


class GithubApp : Application() {

    @set:VisibleForTesting
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}

val Context.component: AppComponent
    get() = (applicationContext as GithubApp).component

val Fragment.component: AppComponent
    get() = activity.component
