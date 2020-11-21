
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.facematch.data.network.NetworkDao
import com.android.facematch.data.repos.UsersRepos

class ViewModelFactory(private val networkDao: NetworkDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UsersRepos(networkDao)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}