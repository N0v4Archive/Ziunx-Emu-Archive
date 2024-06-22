package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.modules.SerializersModule;

/* loaded from: classes.dex */
public class StreamingJsonDecoder extends AbstractDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private DiscriminatorHolder discriminatorHolder;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final AbstractJsonLexer lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    /* loaded from: classes.dex */
    public static final class DiscriminatorHolder {
        public String discriminatorToSkip;
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            try {
                iArr[WriteMode.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WriteMode.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WriteMode.OBJ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StreamingJsonDecoder(Json json, WriteMode mode, AbstractJsonLexer lexer, SerialDescriptor descriptor, DiscriminatorHolder discriminatorHolder) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = json.getSerializersModule();
        this.currentIndex = -1;
        this.discriminatorHolder = discriminatorHolder;
        JsonConfiguration configuration = json.getConfiguration();
        this.configuration = configuration;
        this.elementMarker = configuration.getExplicitNulls() ? null : new JsonElementMarker(descriptor);
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() != 4) {
            return;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private final boolean coerceInputValue(SerialDescriptor serialDescriptor, int i) {
        String peekString;
        Json json = this.json;
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(i);
        if (!elementDescriptor.isNullable() && this.lexer.tryConsumeNull(true)) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && this.lexer.tryConsumeNull(false)) || (peekString = this.lexer.peekString(this.configuration.isLenient())) == null || JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, peekString) != -3)) {
            return false;
        }
        this.lexer.consumeString();
        return true;
    }

    private final int decodeListIndex() {
        boolean tryConsumeComma = this.lexer.tryConsumeComma();
        if (!this.lexer.canConsumeValue()) {
            if (!tryConsumeComma) {
                return -1;
            }
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i = this.currentIndex;
        if (i != -1 && !tryConsumeComma) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i2 = i + 1;
        this.currentIndex = i2;
        return i2;
    }

    private final int decodeMapIndex() {
        int i;
        int i2;
        int i3 = this.currentIndex;
        boolean z = false;
        boolean z2 = i3 % 2 != 0;
        if (!z2) {
            this.lexer.consumeNextToken(':');
        } else if (i3 != -1) {
            z = this.lexer.tryConsumeComma();
        }
        if (!this.lexer.canConsumeValue()) {
            if (!z) {
                return -1;
            }
            AbstractJsonLexer.fail$default(this.lexer, "Expected '}', but had ',' instead", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (z2) {
            if (this.currentIndex == -1) {
                AbstractJsonLexer abstractJsonLexer = this.lexer;
                boolean z3 = !z;
                i2 = abstractJsonLexer.currentPosition;
                if (!z3) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer, "Unexpected trailing comma", i2, null, 4, null);
                    throw new KotlinNothingValueException();
                }
            } else {
                AbstractJsonLexer abstractJsonLexer2 = this.lexer;
                i = abstractJsonLexer2.currentPosition;
                if (!z) {
                    AbstractJsonLexer.fail$default(abstractJsonLexer2, "Expected comma after the key-value pair", i, null, 4, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        int i4 = this.currentIndex + 1;
        this.currentIndex = i4;
        return i4;
    }

    private final int decodeObjectIndex(SerialDescriptor serialDescriptor) {
        boolean z;
        boolean tryConsumeComma = this.lexer.tryConsumeComma();
        while (this.lexer.canConsumeValue()) {
            String decodeStringKey = decodeStringKey();
            this.lexer.consumeNextToken(':');
            int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(serialDescriptor, this.json, decodeStringKey);
            boolean z2 = false;
            if (jsonNameIndex == -3) {
                z2 = true;
                z = false;
            } else {
                if (!this.configuration.getCoerceInputValues() || !coerceInputValue(serialDescriptor, jsonNameIndex)) {
                    JsonElementMarker jsonElementMarker = this.elementMarker;
                    if (jsonElementMarker != null) {
                        jsonElementMarker.mark$kotlinx_serialization_json(jsonNameIndex);
                    }
                    return jsonNameIndex;
                }
                z = this.lexer.tryConsumeComma();
            }
            tryConsumeComma = z2 ? handleUnknown(decodeStringKey) : z;
        }
        if (tryConsumeComma) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        JsonElementMarker jsonElementMarker2 = this.elementMarker;
        if (jsonElementMarker2 != null) {
            return jsonElementMarker2.nextUnmarkedIndex$kotlinx_serialization_json();
        }
        return -1;
    }

    private final String decodeStringKey() {
        boolean isLenient = this.configuration.isLenient();
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        return isLenient ? abstractJsonLexer.consumeStringLenientNotNull() : abstractJsonLexer.consumeKeyString();
    }

    private final boolean handleUnknown(String str) {
        if (this.configuration.getIgnoreUnknownKeys() || trySkip(this.discriminatorHolder, str)) {
            this.lexer.skipElement(this.configuration.isLenient());
        } else {
            this.lexer.failOnUnknownKey(str);
        }
        return this.lexer.tryConsumeComma();
    }

    private final void skipLeftoverElements(SerialDescriptor serialDescriptor) {
        do {
        } while (decodeElementIndex(serialDescriptor) != -1);
    }

    private final boolean trySkip(DiscriminatorHolder discriminatorHolder, String str) {
        if (discriminatorHolder == null || !Intrinsics.areEqual(discriminatorHolder.discriminatorToSkip, str)) {
            return false;
        }
        discriminatorHolder.discriminatorToSkip = null;
        return true;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        StreamingJsonDecoder streamingJsonDecoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode switchMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.path.pushDescriptor(descriptor);
        this.lexer.consumeNextToken(switchMode.begin);
        checkLeadingComma();
        int i = WhenMappings.$EnumSwitchMapping$0[switchMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            streamingJsonDecoder = new StreamingJsonDecoder(this.json, switchMode, this.lexer, descriptor, this.discriminatorHolder);
        } else {
            if (this.mode == switchMode && this.json.getConfiguration().getExplicitNulls()) {
                return this;
            }
            streamingJsonDecoder = new StreamingJsonDecoder(this.json, switchMode, this.lexer, descriptor, this.discriminatorHolder);
        }
        return streamingJsonDecoder;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        boolean isLenient = this.configuration.isLenient();
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        return isLenient ? abstractJsonLexer.consumeBooleanLenient() : abstractJsonLexer.consumeBoolean();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        int decodeListIndex = i != 2 ? i != 4 ? decodeListIndex() : decodeObjectIndex(descriptor) : decodeMapIndex();
        if (this.mode != WriteMode.MAP) {
            this.lexer.path.updateDescriptorIndex(decodeListIndex);
        }
        return decodeListIndex;
    }

    public Object decodeSerializableValue(DeserializationStrategy deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        try {
            return deserializer.deserialize(this);
        } catch (MissingFieldException e) {
            throw new MissingFieldException(e.getMissingFields(), e.getMessage() + " at path: " + this.lexer.path.getPath(), e);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        boolean isLenient = this.configuration.isLenient();
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        return isLenient ? abstractJsonLexer.consumeStringLenientNotNull() : abstractJsonLexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.json.getConfiguration().getIgnoreUnknownKeys() && descriptor.getElementsCount() == 0) {
            skipLeftoverElements(descriptor);
        }
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
    }
}
