package com.foxek.simpletimer.presentation.workout

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.foxek.simpletimer.R
import com.foxek.simpletimer.data.UserPreferences
import com.foxek.simpletimer.data.model.Workout
import com.foxek.simpletimer.presentation.base.BaseFragment
import com.foxek.simpletimer.presentation.changelog.ChangeLogDialog
import com.foxek.simpletimer.presentation.interval.IntervalFragment
import com.foxek.simpletimer.presentation.workout.adapter.WorkoutAdapter
import com.foxek.simpletimer.presentation.workout.dialog.WorkoutCreateDialog
import com.foxek.simpletimer.common.utils.Constants.CHANGELOG_VERSION_VALUE
import com.foxek.simpletimer.common.utils.Constants.EXTRA_WORKOUT_ID
import com.foxek.simpletimer.common.utils.Constants.EXTRA_WORKOUT_NAME
import kotlinx.android.synthetic.main.fragment_workout.*
import javax.inject.Inject


class WorkoutFragment : BaseFragment(), WorkoutContact.View, WorkoutAdapter.Callback {

    override val layoutId = R.layout.fragment_workout

    @Inject
    lateinit var presenter: WorkoutContact.Presenter

    @Inject
    lateinit var userPreferences: UserPreferences

    @Inject
    lateinit var viewAdapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component?.inject(this)
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.viewIsReady()

        fragment_workout_create_btn.setOnClickListener {
            presenter.onCreateButtonClick()
        }

        fragment_workout_list.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = viewAdapter
        }

        viewAdapter.setCallback(this)
        showChangelogDialogIfNeeded()
    }

    override fun startIntervalFragment(workoutId: Int) {
        val args = Bundle().apply {
            putInt(EXTRA_WORKOUT_ID, workoutId)
        }

        executeInActivity { replaceFragment(IntervalFragment(), args) }
    }

    override fun renderWorkoutList(items: List<Workout>) {
        viewAdapter.submitList(items)
    }

    override fun showCreateDialog() {
        showDialog(WorkoutCreateDialog.newInstance())
    }

    override fun onListItemClick(workout: Workout) {
        presenter.onWorkoutItemClick(workout)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun showChangelogDialogIfNeeded() {
        var changelogVersion = 0

        PreferenceManager.getDefaultSharedPreferences(context).run {
            changelogVersion = userPreferences.changelogVersion
            userPreferences.changelogVersion = CHANGELOG_VERSION_VALUE
        }

        if (changelogVersion < CHANGELOG_VERSION_VALUE) {
            showDialog(ChangeLogDialog())
        }
    }
}