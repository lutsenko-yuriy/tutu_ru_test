package com.example.yurich.tuturutest.repository.local_storage

import com.example.yurich.tuturutest.repository.DataStorage
import io.realm.Realm
import io.realm.RealmResults
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
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
        realm = Realm.getDefaultInstance()
        return Observable.create<RealmResults<StoragedStation>> {
                    subscriber ->
                    realm = Realm.getDefaultInstance()
                    subscriber.onNext(
                            realm!!.where(StoragedStation::class.java)
                                    .findAll()
                    )
                    subscriber.onCompleted()
                }
                .concatMap {
                    Observable.from(it)
                }
                .subscribeOn(Schedulers.io())
    }

    override fun putStation(station: StoragedStation) {
        realm = Realm.getDefaultInstance()
        realm!!.executeTransaction {
            realm!!.copyToRealmOrUpdate(station)
        }
        realm!!.close()
    }

    override fun clearDatabase() {
        realm = Realm.getDefaultInstance()
        realm!!.executeTransaction {
            realm!!.delete(StoragedStation::class.java)
        }
        realm!!.close()
    }
}