/*
 * Copyright (C) 2020 Muntashir Al-Islam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.muntashirakon.AppManager.scanner;

import android.content.Context;

import com.google.classysharkandroid.dex.DexLoaderBuilder;

import java.io.File;
import java.util.Set;

import androidx.annotation.NonNull;
import io.github.muntashirakon.AppManager.scanner.reflector.Reflector;

public class DexClasses {
    ClassLoader loader;

    public DexClasses(@NonNull Context ctx, byte[] dexBytes) {
        loader = DexLoaderBuilder.fromBytes(ctx, dexBytes);
    }

    public DexClasses(@NonNull Context ctx, File dexFile) {
        loader = DexLoaderBuilder.fromFile(ctx, dexFile);
    }

    public Class<?> loadClass(String className) throws ClassNotFoundException {
        return loader.loadClass(className);
    }

    public Set<String> getImports(String className) throws ClassNotFoundException {
        return new Reflector(loadClass(className)).getImports();
    }

    public Reflector getReflector(String className) throws ClassNotFoundException {
        return new Reflector(loadClass(className));
    }
}
