
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.facematch.data.model.Users
import com.android.facematch.data.repos.UsersRepos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Abhishek.s on 26,October,2020
 */
class UserViewModel(private val userRepos: UsersRepos) : ViewModel() {

    private val users = MutableLiveData<Resource<Users>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            userRepos.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                }, {
                    users.postValue(Resource.error("Service Broken", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers(): LiveData<Resource<Users>> {
        return users
    }
}