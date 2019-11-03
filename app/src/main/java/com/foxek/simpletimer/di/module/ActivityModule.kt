package com.foxek.simpletimer.di.module

import com.foxek.simpletimer.ui.interval.adapter.IntervalAdapter
import com.foxek.simpletimer.ui.interval.IntervalContact
import com.foxek.simpletimer.ui.interval.IntervalInteractor
import com.foxek.simpletimer.ui.interval.IntervalPresenter
import com.foxek.simpletimer.ui.timer.TimerContact
import com.foxek.simpletimer.ui.timer.TimerInteractor
import com.foxek.simpletimer.ui.timer.TimerPresenter
import com.foxek.simpletimer.ui.workout.adapter.WorkoutAdapter
import com.foxek.simpletimer.ui.workout.WorkoutContact
import com.foxek.simpletimer.ui.workout.WorkoutInteractor
import com.foxek.simpletimer.ui.workout.WorkoutPresenter

import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    /* Workout Activity */
    @Provides
    fun provideWorkoutAdapter(): WorkoutAdapter = WorkoutAdapter()

    @Provides
    fun provideWorkoutInteractor(interactor: WorkoutInteractor): WorkoutContact.Interactor = interactor

    @Provides
    fun provideWorkoutPresenter(presenter: WorkoutPresenter): WorkoutContact.Presenter = presenter


    /* Interval Activity */
    @Provides
    fun provideIntervalAdapter(): IntervalAdapter = IntervalAdapter()

    @Provides
    fun provideIntervalInteractor(interactor: IntervalInteractor): IntervalContact.Interactor = interactor


    @Provides
    fun provideIntervalPresenter(presenter: IntervalPresenter): IntervalContact.Presenter = presenter

    /* Timer Activity */
    @Provides
    fun provideTimerInteractor(interactor: TimerInteractor): TimerContact.Interactor = interactor


    @Provides
    fun provideTimerPresenter(presenter: TimerPresenter): TimerContact.Presenter = presenter
}
