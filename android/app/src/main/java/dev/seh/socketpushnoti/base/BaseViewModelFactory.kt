package dev.seh.socketpushnoti.base;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.seh.socketpushnoti.repository.Repository
import java.lang.reflect.InvocationTargetException

/**
 * @author : seungHo
 * @since : 2022-01-13
 * class : BaseViewModelFactory.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class BaseViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(Repository::class.java)
                    .newInstance(repository)

            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
        return super.create(modelClass)
    }
}