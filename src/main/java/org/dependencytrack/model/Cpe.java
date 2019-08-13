/*
 * This file is part of Dependency-Track.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright (c) Steve Springett. All Rights Reserved.
 */
package org.dependencytrack.model;

import alpine.validation.RegexSequence;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Model class for Common Platform Enumeration (CPE).
 *
 * @author Steve Springett
 * @since 3.6.0
 */
@PersistenceCapable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cpe implements Serializable {

    private static final long serialVersionUID = 1598287997098587015L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    @JsonIgnore
    private long id;

    @Persistent
    @Column(name = "CPE22", jdbcType = "VARCHAR")
    @Index(name = "CPE_CPE22_IDX")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The cpe22 may only contain printable characters")
    private String cpe22;

    @Persistent
    @Column(name = "CPE23", jdbcType = "VARCHAR", allowsNull = "false")
    @Index(name = "CPE_CPE23_IDX", unique = "true")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The cpe23 may only contain printable characters")
    private String cpe23;

    @Persistent
    @Column(name = "TITLE", jdbcType = "VARCHAR")

    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The title may only contain printable characters")
    private String title;

    @Persistent
    @Column(name = "PART", jdbcType = "VARCHAR")
    @Size(max = 1)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The part may only contain printable characters")
    private String part;

    @Persistent
    @Column(name = "VENDOR", jdbcType = "VARCHAR")
    @Index(name = "CPE_VENDOR_IDX")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The vendor may only contain printable characters")
    private String vendor;

    @Persistent
    @Column(name = "PRODUCT", jdbcType = "VARCHAR")
    @Index(name = "CPE_PRODUCT_IDX")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The product may only contain printable characters")
    private String product;

    @Persistent
    @Column(name = "VERSION", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The version may only contain printable characters")
    private String version;

    @Persistent
    @Column(name = "UPDATE", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The update may only contain printable characters")
    private String update;

    @Persistent
    @Column(name = "EDITION", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The edition may only contain printable characters")
    private String edition;

    @Persistent
    @Column(name = "LANGUAGE", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The language may only contain printable characters")
    private String language;

    @Persistent
    @Column(name = "SWEDITION", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The swEdition may only contain printable characters")
    private String swEdition;

    @Persistent
    @Column(name = "TARGETSW", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The targetSw may only contain printable characters")
    private String targetSw;

    @Persistent
    @Column(name = "TARGETHW", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The targetHw may only contain printable characters")
    private String targetHw;

    @Persistent
    @Column(name = "OTHER", jdbcType = "VARCHAR")
    @Size(max = 255)
    @Pattern(regexp = RegexSequence.Definition.PRINTABLE_CHARS, message = "The other may only contain printable characters")
    private String other;

    @Persistent
    @Column(name = "OFFICIAL")
    private boolean official;

    @Persistent(mappedBy = "cpe")
    @Order(extensions = @Extension(vendorName = "datanucleus", key = "list-ordering", value = "id ASC"))
    private Collection<CpeReference> references;

    @Persistent(table = "CPE_VULNERABILITIES", mappedBy = "affectedCpes")
    @Join(column = "CPE_ID")
    @Element(column = "VULNERABILITY_ID", dependent = "false")
    @Order(extensions = @Extension(vendorName = "datanucleus", key = "list-ordering", value = "id ASC"))
    private List<Vulnerability> vulnerabilities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpe22() {
        return cpe22;
    }

    public void setCpe22(String cpe22) {
        this.cpe22 = cpe22;
    }

    public String getCpe23() {
        return cpe23;
    }

    public void setCpe23(String cpe23) {
        this.cpe23 = cpe23;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSwEdition() {
        return swEdition;
    }

    public void setSwEdition(String swEdition) {
        this.swEdition = swEdition;
    }

    public String getTargetSw() {
        return targetSw;
    }

    public void setTargetSw(String targetSw) {
        this.targetSw = targetSw;
    }

    public String getTargetHw() {
        return targetHw;
    }

    public void setTargetHw(String targetHw) {
        this.targetHw = targetHw;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public Collection<CpeReference> getReferences() {
        return references;
    }

    public void setReferences(Collection<CpeReference> references) {
        this.references = references;
    }

    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public void addVulnerability(Vulnerability vulnerability) {
        if (this.vulnerabilities == null) {
            this.vulnerabilities = new ArrayList<>();
        }
        this.vulnerabilities.add(vulnerability);
    }
}