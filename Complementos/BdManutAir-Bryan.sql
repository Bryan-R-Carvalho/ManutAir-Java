PGDMP     ,    ;                y         
   bdmanutair    12.5    12.5 ,    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            @           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            A           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            B           1262    32919 
   bdmanutair    DATABASE     ?   CREATE DATABASE bdmanutair WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE bdmanutair;
                postgres    false            ?            1259    33228    contrato    TABLE     ?   CREATE TABLE public.contrato (
    id bigint NOT NULL,
    data_inicio character varying,
    prazo character varying,
    pessoa_id bigint
);
    DROP TABLE public.contrato;
       public         heap    postgres    false            ?            1259    33226    contrato_id_seq    SEQUENCE     x   CREATE SEQUENCE public.contrato_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.contrato_id_seq;
       public          postgres    false    208            C           0    0    contrato_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.contrato_id_seq OWNED BY public.contrato.id;
          public          postgres    false    207            ?            1259    32920    equipamento    TABLE     ?   CREATE TABLE public.equipamento (
    marca character varying,
    modelo character varying,
    numeroserie character varying NOT NULL
);
    DROP TABLE public.equipamento;
       public         heap    postgres    false            ?            1259    33273    equipamentocontrato    TABLE     ?   CREATE TABLE public.equipamentocontrato (
    equipamento_numero character varying NOT NULL,
    contrato_id bigint NOT NULL
);
 '   DROP TABLE public.equipamentocontrato;
       public         heap    postgres    false            ?            1259    33249    os    TABLE     "  CREATE TABLE public.os (
    id bigint NOT NULL,
    descricao character varying,
    endereco character varying,
    dia character varying,
    hora character varying,
    contrato_id bigint,
    equipamento_numero character varying,
    tecnico_id bigint,
    estado character varying
);
    DROP TABLE public.os;
       public         heap    postgres    false            ?            1259    33247 	   os_id_seq    SEQUENCE     r   CREATE SEQUENCE public.os_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.os_id_seq;
       public          postgres    false    210            D           0    0 	   os_id_seq    SEQUENCE OWNED BY     7   ALTER SEQUENCE public.os_id_seq OWNED BY public.os.id;
          public          postgres    false    209            ?            1259    32936    pessoa    TABLE       CREATE TABLE public.pessoa (
    endereco character varying,
    telefone character varying,
    documento character varying NOT NULL,
    nome character varying NOT NULL,
    razaosocial character varying,
    tipo character varying,
    id bigint NOT NULL
);
    DROP TABLE public.pessoa;
       public         heap    postgres    false            ?            1259    33021    pessoa_id_seq    SEQUENCE     v   CREATE SEQUENCE public.pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.pessoa_id_seq;
       public          postgres    false    203            E           0    0    pessoa_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.pessoa_id_seq OWNED BY public.pessoa.id;
          public          postgres    false    206            ?            1259    32964    tecnico    TABLE     T   CREATE TABLE public.tecnico (
    id bigint NOT NULL,
    nome character varying
);
    DROP TABLE public.tecnico;
       public         heap    postgres    false            ?            1259    32962    tecnico_id_seq    SEQUENCE     w   CREATE SEQUENCE public.tecnico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.tecnico_id_seq;
       public          postgres    false    205            F           0    0    tecnico_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.tecnico_id_seq OWNED BY public.tecnico.id;
          public          postgres    false    204            ?
           2604    33231    contrato id    DEFAULT     j   ALTER TABLE ONLY public.contrato ALTER COLUMN id SET DEFAULT nextval('public.contrato_id_seq'::regclass);
 :   ALTER TABLE public.contrato ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    207    208    208            ?
           2604    33252    os id    DEFAULT     ^   ALTER TABLE ONLY public.os ALTER COLUMN id SET DEFAULT nextval('public.os_id_seq'::regclass);
 4   ALTER TABLE public.os ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            ?
           2604    33023 	   pessoa id    DEFAULT     f   ALTER TABLE ONLY public.pessoa ALTER COLUMN id SET DEFAULT nextval('public.pessoa_id_seq'::regclass);
 8   ALTER TABLE public.pessoa ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    203            ?
           2604    32967 
   tecnico id    DEFAULT     h   ALTER TABLE ONLY public.tecnico ALTER COLUMN id SET DEFAULT nextval('public.tecnico_id_seq'::regclass);
 9   ALTER TABLE public.tecnico ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            9          0    33228    contrato 
   TABLE DATA           E   COPY public.contrato (id, data_inicio, prazo, pessoa_id) FROM stdin;
    public          postgres    false    208   ?1       3          0    32920    equipamento 
   TABLE DATA           A   COPY public.equipamento (marca, modelo, numeroserie) FROM stdin;
    public          postgres    false    202   ?1       <          0    33273    equipamentocontrato 
   TABLE DATA           N   COPY public.equipamentocontrato (equipamento_numero, contrato_id) FROM stdin;
    public          postgres    false    211   #2       ;          0    33249    os 
   TABLE DATA           u   COPY public.os (id, descricao, endereco, dia, hora, contrato_id, equipamento_numero, tecnico_id, estado) FROM stdin;
    public          postgres    false    210   X2       4          0    32936    pessoa 
   TABLE DATA           \   COPY public.pessoa (endereco, telefone, documento, nome, razaosocial, tipo, id) FROM stdin;
    public          postgres    false    203   ?2       6          0    32964    tecnico 
   TABLE DATA           +   COPY public.tecnico (id, nome) FROM stdin;
    public          postgres    false    205   *3       G           0    0    contrato_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.contrato_id_seq', 6, true);
          public          postgres    false    207            H           0    0 	   os_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('public.os_id_seq', 3, true);
          public          postgres    false    209            I           0    0    pessoa_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.pessoa_id_seq', 1, true);
          public          postgres    false    206            J           0    0    tecnico_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tecnico_id_seq', 12, true);
          public          postgres    false    204            ?
           2606    33236    contrato contrato_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT contrato_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.contrato DROP CONSTRAINT contrato_pkey;
       public            postgres    false    208            ?
           2606    32927    equipamento equipamento_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT equipamento_pkey PRIMARY KEY (numeroserie);
 F   ALTER TABLE ONLY public.equipamento DROP CONSTRAINT equipamento_pkey;
       public            postgres    false    202            ?
           2606    33280 ,   equipamentocontrato equipamentocontrato_pkey 
   CONSTRAINT     ?   ALTER TABLE ONLY public.equipamentocontrato
    ADD CONSTRAINT equipamentocontrato_pkey PRIMARY KEY (equipamento_numero, contrato_id);
 V   ALTER TABLE ONLY public.equipamentocontrato DROP CONSTRAINT equipamentocontrato_pkey;
       public            postgres    false    211    211            ?
           2606    33035    pessoa id_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT id_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT id_pkey;
       public            postgres    false    203            ?
           2606    33257 
   os os_pkey 
   CONSTRAINT     H   ALTER TABLE ONLY public.os
    ADD CONSTRAINT os_pkey PRIMARY KEY (id);
 4   ALTER TABLE ONLY public.os DROP CONSTRAINT os_pkey;
       public            postgres    false    210            ?
           2606    32972    tecnico tecnico_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.tecnico
    ADD CONSTRAINT tecnico_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.tecnico DROP CONSTRAINT tecnico_pkey;
       public            postgres    false    205            ?
           2606    33237     contrato contrato_pessoa_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT contrato_pessoa_id_fkey FOREIGN KEY (pessoa_id) REFERENCES public.pessoa(id);
 J   ALTER TABLE ONLY public.contrato DROP CONSTRAINT contrato_pessoa_id_fkey;
       public          postgres    false    208    2726    203            ?
           2606    33286 8   equipamentocontrato equipamentocontrato_contrato_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.equipamentocontrato
    ADD CONSTRAINT equipamentocontrato_contrato_id_fkey FOREIGN KEY (contrato_id) REFERENCES public.contrato(id);
 b   ALTER TABLE ONLY public.equipamentocontrato DROP CONSTRAINT equipamentocontrato_contrato_id_fkey;
       public          postgres    false    208    2730    211            ?
           2606    33281 ?   equipamentocontrato equipamentocontrato_equipamento_numero_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.equipamentocontrato
    ADD CONSTRAINT equipamentocontrato_equipamento_numero_fkey FOREIGN KEY (equipamento_numero) REFERENCES public.equipamento(numeroserie);
 i   ALTER TABLE ONLY public.equipamentocontrato DROP CONSTRAINT equipamentocontrato_equipamento_numero_fkey;
       public          postgres    false    202    2724    211            ?
           2606    33258    os os_contrato_id_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.os
    ADD CONSTRAINT os_contrato_id_fkey FOREIGN KEY (contrato_id) REFERENCES public.contrato(id);
 @   ALTER TABLE ONLY public.os DROP CONSTRAINT os_contrato_id_fkey;
       public          postgres    false    2730    208    210            ?
           2606    33263    os os_equipamento_numero_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.os
    ADD CONSTRAINT os_equipamento_numero_fkey FOREIGN KEY (equipamento_numero) REFERENCES public.equipamento(numeroserie);
 G   ALTER TABLE ONLY public.os DROP CONSTRAINT os_equipamento_numero_fkey;
       public          postgres    false    2724    202    210            ?
           2606    33268    os os_tecnico_id_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.os
    ADD CONSTRAINT os_tecnico_id_fkey FOREIGN KEY (tecnico_id) REFERENCES public.tecnico(id);
 ?   ALTER TABLE ONLY public.os DROP CONSTRAINT os_tecnico_id_fkey;
       public          postgres    false    205    210    2728            9   0   x???  ?w;?P?ܱ??ϡ??P?2*+??:!??????.??	?      3   M   x??1?  ??}?A??.X+!?V??wk(?K??	???C?L?4???u???%?^?$??Mn^p?T	?      <   %   x?+K54J?4?*L60? ?FF??fP?W? ?       ;   R   x?3?(JM?,NTHIU???-H?J?t?I+JM??Q015?44?70?72022?L8M9?R??89]s?R?J??b???? A?      4   `   x?%?A? F????i:? ^?'p3ELhT???_Ӿŷ{v*???D?K=??_X?hE?d?]???1?(?'?z?d/Em?k?u|Nk??]E??      6      x?3??J?M-?24?L?I??????? >?     