/*
 * Copyright (C) 2017 BullyBoo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.newx.utils.encrypt;

import com.newx.utils.encrypt.builders.BuilderAES;
import com.newx.utils.encrypt.builders.BuilderARCFOUR;
import com.newx.utils.encrypt.builders.BuilderBlowfish;
import com.newx.utils.encrypt.builders.BuilderDES;
import com.newx.utils.encrypt.builders.BuilderDESede;
import com.newx.utils.encrypt.builders.BuilderHMAC;
import com.newx.utils.encrypt.builders.BuilderPBE;
import com.newx.utils.encrypt.builders.BuilderRSA;

/**
 * Main Encoder class
 * From this class you can get access to all encryption methods
 */
public class Encoder {

    private Encoder() {

    }

    public static BuilderAES BuilderAES() {
        return new BuilderAES();
    }

    public static BuilderARCFOUR BuilderARCFOUR() {
        return new BuilderARCFOUR();
    }

    public static BuilderDES BuilderDES() {
        return new BuilderDES();
    }

    public static BuilderDESede BuilderDESede() {
        return new BuilderDESede();
    }

    public static BuilderRSA BuilderRSA() {
        return new BuilderRSA();
    }

    public static BuilderHMAC BuilderHMAC() {
        return new BuilderHMAC();
    }

    public static BuilderPBE BuilderPBE() {
        return new BuilderPBE();
    }

    public static BuilderBlowfish BuilderBlowfish() {
        return new BuilderBlowfish();
    }

    public static com.newx.utils.encrypt.hashes.Hash Hashes() {
        return new com.newx.utils.encrypt.hashes.Hash();
    }

}
