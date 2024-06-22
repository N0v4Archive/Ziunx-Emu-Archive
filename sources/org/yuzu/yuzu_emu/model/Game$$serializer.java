package org.yuzu.yuzu_emu.model;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* loaded from: classes.dex */
public final class Game$$serializer implements GeneratedSerializer {
    public static final Game$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Game$$serializer game$$serializer = new Game$$serializer();
        INSTANCE = game$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("org.yuzu.yuzu_emu.model.Game", game$$serializer, 6);
        pluginGeneratedSerialDescriptor.addElement("title", true);
        pluginGeneratedSerialDescriptor.addElement("path", false);
        pluginGeneratedSerialDescriptor.addElement("programId", true);
        pluginGeneratedSerialDescriptor.addElement("developer", true);
        pluginGeneratedSerialDescriptor.addElement("version", true);
        pluginGeneratedSerialDescriptor.addElement("isHomebrew", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Game$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, BooleanSerializer.INSTANCE};
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x004d. Please report as an issue. */
    @Override // kotlinx.serialization.DeserializationStrategy
    public Game deserialize(Decoder decoder) {
        boolean z;
        String str;
        String str2;
        String str3;
        int i;
        String str4;
        String str5;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        if (beginStructure.decodeSequentially()) {
            String decodeStringElement = beginStructure.decodeStringElement(descriptor2, 0);
            String decodeStringElement2 = beginStructure.decodeStringElement(descriptor2, 1);
            String decodeStringElement3 = beginStructure.decodeStringElement(descriptor2, 2);
            String decodeStringElement4 = beginStructure.decodeStringElement(descriptor2, 3);
            String decodeStringElement5 = beginStructure.decodeStringElement(descriptor2, 4);
            str2 = decodeStringElement;
            z = beginStructure.decodeBooleanElement(descriptor2, 5);
            str3 = decodeStringElement4;
            str = decodeStringElement5;
            str4 = decodeStringElement3;
            str5 = decodeStringElement2;
            i = 63;
        } else {
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            boolean z2 = true;
            boolean z3 = false;
            int i2 = 0;
            while (z2) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                switch (decodeElementIndex) {
                    case -1:
                        z2 = false;
                    case 0:
                        str6 = beginStructure.decodeStringElement(descriptor2, 0);
                        i2 |= 1;
                    case 1:
                        str10 = beginStructure.decodeStringElement(descriptor2, 1);
                        i2 |= 2;
                    case 2:
                        str9 = beginStructure.decodeStringElement(descriptor2, 2);
                        i2 |= 4;
                    case 3:
                        str7 = beginStructure.decodeStringElement(descriptor2, 3);
                        i2 |= 8;
                    case 4:
                        str8 = beginStructure.decodeStringElement(descriptor2, 4);
                        i2 |= 16;
                    case 5:
                        z3 = beginStructure.decodeBooleanElement(descriptor2, 5);
                        i2 |= 32;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            z = z3;
            str = str8;
            str2 = str6;
            int i3 = i2;
            str3 = str7;
            i = i3;
            String str11 = str10;
            str4 = str9;
            str5 = str11;
        }
        beginStructure.endStructure(descriptor2);
        return new Game(i, str2, str5, str4, str3, str, z, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Game value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Game.write$Self$app_eaRelease(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
