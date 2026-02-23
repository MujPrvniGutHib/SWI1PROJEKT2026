package cz.osu.swicko1backend.model.repository;

import cz.osu.swicko1backend.model.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AppUserRepository extends CrudRepository<AppUser, UUID> {
//    prostředník pro SQL příkazy

}
