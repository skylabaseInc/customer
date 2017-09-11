/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.customer.service.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentificationCardScanRepository extends JpaRepository<IdentificationCardScanEntity, Long> {

  @Query("SELECT CASE WHEN COUNT(i) > 0 THEN 'true' ELSE 'false' END FROM IdentificationCardScanEntity i WHERE i.identifier = :identifier AND i.identificationCard = :identificationCard")
  Boolean existsByIdentifierAndIdentificationCard(@Param("identifier") final String identifier, @Param("identificationCard") final IdentificationCardEntity identificationCardEntity);

  Optional<IdentificationCardScanEntity> findByIdentifierAndIdentificationCard(final String identifier, final IdentificationCardEntity identificationCardEntity);

  List<IdentificationCardScanEntity> findByIdentificationCard(final IdentificationCardEntity identificationCardEntity);
}
