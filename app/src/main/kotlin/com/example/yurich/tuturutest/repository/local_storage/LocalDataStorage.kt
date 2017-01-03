package com.example.yurich.tuturutest.repository.local_storage

import com.example.yurich.tuturutest.repository.DataStorage
import io.realm.Realm
import io.realm.RealmResults
import rx.Observable
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
class LocalDataStorage @Inject constructor() : DataStorage {

    var realm: Realm? = null

    override fun getStations(): Observable<StoragedStation> {
        return Observable.create<RealmResults<StoragedStation>> {
            subscriber ->

            try {
                realm = getRealmInstance()
                subscriber.onNext(
                        realm!!.where(StoragedStation::class.java)
                                .findAll()
                )
                subscriber.onCompleted()
            } catch (e: Throwable) {
                subscriber.onError(e)
            }
        }.concatMap {
            Observable.from(it)
        }.doOnCompleted {
            closeRealmInstance()
        }.doOnError {
            closeRealmInstance()
        }
    }

    override fun putStation(station: StoragedStation) {
        realm = getRealmInstance()
        realm!!.executeTransaction {
            realm!!.copyToRealmOrUpdate(station)
        }
        closeRealmInstance()
    }

    override fun clearDatabase() {
        realm = getRealmInstance()
        realm!!.executeTransaction {
            realm!!.delete(StoragedStation::class.java)
        }
        closeRealmInstance()
    }

    private fun closeRealmInstance() {
        realm!!.close()
    }

    private fun getRealmInstance() = Realm.getDefaultInstance()
}